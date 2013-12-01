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

  private static final Logger logger = LoggerFactory.getLogger(MappingFactory.class);

  private List<Mapping> mappings = new ArrayList<>();

  public Mapping readMapping(String filename) {
    List<String> lines = readLines(filename);

    Mapping mapping = buildMapping(lines);
    return mapping;
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

  private Mapping buildMapping(List<String> lines) {
    Mapping mapping = new Mapping();
    List<MappingProperty> properties = new ArrayList<>();
    mapping.setMappingProperties(properties);

    Class<?> clazz = null;

    for (String line : lines) {
      if (line.startsWith("#") || !line.contains("=")) {
        continue;
      }

      String[] commands = line.split(",");
      String type = commands[0];
      String[] keyValuePair = type.split("=");
      switch (keyValuePair[0]) {
        case "class":
          String className = keyValuePair[1];
          mapping.setClassName(className);
          clazz = createClass(className);
          break;
        case "size":
          int size = Integer.parseInt(keyValuePair[1]);
          mapping.setSize(size);
          break;
        case "property":
          MappingProperty property = buildMappingProperty(clazz, line);
          properties.add(property);
          break;
        default:
          logger.error("Unknown command: {}. Lines must start with 'class', 'size' or 'property'. Skipping line {}", commands[0], line);
          break;
      }
    }

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

  private MappingProperty buildMappingProperty(Class<?> clazz, String line) {
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
    int offset = Integer.parseInt(tokenMap.get("offset"));

    Field field = getField(clazz, fieldName);
    Class<?> fieldType = field.getType();
    String fieldTypeName = fieldType.getName();

    if (property.endsWith("[]")) {

      int times = Integer.parseInt(tokenMap.get("times"));
      result = new ArrayMappingProperty(fieldName, offset, times);

    } else if (fieldType.isAssignableFrom(String.class)) {

      int length = Integer.parseInt(tokenMap.get("length"));
      result = new StringMappingProperty(property, offset, length);

    } else if ("byte".equals(fieldTypeName) || "short".equals(fieldTypeName) || "int".equals(fieldTypeName)) {

      result = new MappingProperty(property, offset);

    } else if (mapping != null) {

      Mapping subMapping = readMapping(mapping + ".mapping");
      int times = Integer.parseInt(tokenMap.get("times"));
      result = new SubMappingProperty(property, offset, subMapping, times);
    } else {

      System.out.println("hihi " + property);
      result = new MappingProperty(property, offset);

    }
    return result;
  }

  private Field getField(Class<?> clazz, String fieldName) {
    Field field;
    try {
      field = clazz.getDeclaredField(fieldName);
      return field;
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Field " + fieldName + " not found!. Unable to create mapping!");
    } catch (SecurityException e) {
      throw new RuntimeException("Security exception: " + e.getMessage() + ". Unable to create mapping!");
    }
  }
}
