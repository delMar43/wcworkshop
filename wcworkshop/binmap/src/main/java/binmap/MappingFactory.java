package binmap;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingFactory {
  private static final MappingFactory instance = new MappingFactory();
  private static final Logger logger = LoggerFactory.getLogger(MappingFactory.class);

  private MappingFactory() {
  }

  public Mapping createMapping(String filename) {
    try {
      List<String> lines = readLines(filename);

      Mapping mapping = buildMapping(filename, lines);
      return mapping;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private List<String> readLines(String filename) {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
    try {
      List<String> lines = IOUtils.readLines(inputStream);
      return lines;
    } catch (IOException e) {
      throw new RuntimeException("Exception while trying to read " + filename + ": " + e.getMessage());
    }
  }

  private Mapping buildMapping(String filename, List<String> lines) {
    Class<?> clazz = null;

    String className = null;
    int size = -1;
    Integer offsetStart = null;
    boolean withFilesize = false;
    List<MappingProperty> properties = new ArrayList<>();
    Map<String, String> dynamicOffsets = new HashMap<>();
    for (String line : lines) {
      if (line.startsWith("#") || !line.contains("=")) {
        continue;
      }

      String[] commands = line.split(",");
      String type = commands[0];
      String[] keyValuePair = type.split("=");
      switch (keyValuePair[0]) {
        case "class":
          className = keyValuePair[1];
          clazz = createClass(className);
          break;
        case "size":
          if ("dynamic".equals(keyValuePair[1])) {
            size = Constants.SIZE_DYNAMIC;
          } else {
            size = Integer.parseInt(keyValuePair[1]);
          }
          break;
        case "offsetStart":
          offsetStart = Integer.valueOf(keyValuePair[1]);
          break;
        case "filesize":
          withFilesize = Boolean.valueOf(keyValuePair[1]);
          break;
        case "property":
          MappingProperty property = buildMappingProperty(clazz, line, dynamicOffsets);
          properties.add(property);
          break;
        default:
          logger.error("Unknown command: {}. Lines must start with 'class', 'size' or 'property'. Skipping line {}", commands[0], line);
          break;
      }
    }
    Mapping mapping = new Mapping(filename, className, size, properties, dynamicOffsets, withFilesize, offsetStart);

    return mapping;
  }

  private Class<?> createClass(String className) {
    Class<?> clazz;
    try {
      clazz = Class.forName(className);
      return clazz;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Class " + className + " not found! Unable to create mapping!");
    }
  }

  private MappingProperty buildMappingProperty(Class<?> clazz, String line, Map<String, String> offsets) {
    MappingProperty result = null;

    Map<String, String> tokenMap = new HashMap<>();
    String[] tokens = line.split(",");
    for (String token : tokens) {
      String[] keyValue = token.split("=");
      String key = keyValue[0];
      String value = keyValue[1];

      tokenMap.put(key, value);
    }

    String property = tokenMap.get("property");
    String mapping = tokenMap.get("mapping");
    String fieldName = property.replace("[]", "");

    int offset;
    String offsetString = tokenMap.get("offset");
    if (offsetString.startsWith("{")) {
      offset = Constants.OFFSET_BLOCK;
      offsets.put(property, offsetString);
    } else if (offsetString.equals("calc")) {
      offset = Constants.OFFSET_CALC;
    } else {
      offset = Integer.parseInt(tokenMap.get("offset"));
    }

    int times;
    if (tokenMap.containsKey("times")) {
      String timesString = tokenMap.get("times");
      if (timesString.startsWith("{")) {
        timesString = timesString.replace("{", "").replace("}", "");
        if ("offsets".equals(timesString)) {
          times = Constants.TIMES_OFFSETS;
        } else if ("all".equals(timesString)) {
          times = Constants.TIMES_ALL;
        } else if ("toTheEnd".equals(timesString)) {
          times = Constants.TIMES_TOTHEEND;
        } else if ("currentBlock".equals(timesString)) {
          times = Constants.TIMES_CURRENTBLOCK;
        } else {
          throw new RuntimeException("Unknown times pattern: " + timesString);
        }
      } else {
        times = Integer.parseInt(tokenMap.get("times"));
      }
    } else {
      times = 1;
    }

    String isBlockOffsetCreator;
    if (tokenMap.containsKey("createsOffset")) {
      isBlockOffsetCreator = tokenMap.get("createsOffset");
    } else {
      isBlockOffsetCreator = null;
    }

    Field field = getField(clazz, fieldName);
    Class<?> fieldType = field.getType();
    String fieldTypeName;
    if (fieldType.isArray()) {
      fieldTypeName = fieldType.getComponentType().getName();
    } else {
      fieldTypeName = fieldType.getName();
    }

    if ("java.lang.String".equals(fieldTypeName)) {

      if (tokenMap.containsKey("length")) {
        int length = Integer.parseInt(tokenMap.get("length"));
        result = new StringMappingProperty(property, offset, length, times, isBlockOffsetCreator);
      } else {
        result = new NullTermStringMappingProperty(property, offset, times, isBlockOffsetCreator);
      }

    } else if ("byte".equals(fieldTypeName) || "short".equals(fieldTypeName)) {

      result = new MappingProperty(property, offset, times, isBlockOffsetCreator);

    } else if ("int".equals(fieldTypeName)) {

      boolean onlyThreeBytes = tokenMap.containsKey("length") && tokenMap.get("length").equals("3");
      result = new IntegerMappingProperty(property, offset, times, onlyThreeBytes, isBlockOffsetCreator);

    } else if (mapping != null) {

      Mapping subMapping = createMapping(mapping + ".mapping");
      result = new SubMappingProperty(property, offset, subMapping, times, isBlockOffsetCreator);
    } else {
      throw new RuntimeException("Unable to parse line: " + line);
    }
    return result;
  }

  private Field getField(Class<?> clazz, String fieldName) {
    Field field;
    try {
      field = clazz.getDeclaredField(fieldName);
      return field;
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Field " + fieldName + " in class " + clazz.getName() + " not found!. Unable to create mapping!");
    } catch (SecurityException e) {
      throw new RuntimeException("Security exception: " + e.getMessage() + ". Unable to create mapping!");
    }
  }

  public static MappingFactory getInstance() {
    return instance;
  }
}
