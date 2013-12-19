package binmap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryWriter {
  private static final Logger logger = LoggerFactory.getLogger(BinaryWriter.class);
  private static final BinaryWriter instance = new BinaryWriter();
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  private BinaryWriter() {
  }

  public byte[] toDynamicBinary(Object source, Mapping mapping) {
    List<Byte> byteList = new ArrayList<>();

    List<Integer> offsets = new ArrayList<>();
    int previousOffset = 0;
    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName = getFieldName(property);
      Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());
      Field field = binaryUtils.getField(targetClass, fieldName);
      // Class<?> fieldType = field.getType();
      // String fieldTypeName = getFieldTypeName(fieldType);

      Object value = binaryUtils.getValue(source, field);
      if (value == null) {
        // offsets.add(previousOffset);
        continue;
      }
      boolean propertyIsArray = property.getProperty().endsWith("[]");
      int times;
      if (propertyIsArray) {
        times = Array.getLength(value);
      } else {
        times = property.getTimes();
      }

      MappingProperty dynamicProperty;
      if (property instanceof SubMappingProperty) {

        Mapping subMapping = ((SubMappingProperty) property).getSubMapping();
        dynamicProperty = new SubMappingProperty(property.getProperty(), property.getOffset(), subMapping, times,
            property.isBlockOffsetCreator());

      } else if (property instanceof StringMappingProperty) {

        dynamicProperty = new StringMappingProperty(property.getProperty(), property.getOffset(),
            ((StringMappingProperty) property).getLength(), property.getTimes(), property.isBlockOffsetCreator());

      } else {

        dynamicProperty = new MappingProperty(property.getProperty(), property.getOffset(), times, property.isBlockOffsetCreator());

      }

      List<byte[]> propertyByteList;
      if (property.getProperty().equals("empty")) {
        propertyByteList = Collections.EMPTY_LIST;
      } else {
        propertyByteList = handleProperty(source, mapping, dynamicProperty);
      }
      if (propertyByteList.isEmpty()) {

        if (property.isBlockOffsetCreator() != null) {
          offsets.add(previousOffset);
        }

      } else {
        boolean first = true;
        for (byte[] propertyBytes : propertyByteList) {
          for (byte b : propertyBytes) {
            byteList.add(b);
          }

          boolean createBlockOffset = property.isBlockOffsetCreator() != null;
          if (createBlockOffset) {
            if (property.isBlockOffsetCreator().equals("first") && first || property.isBlockOffsetCreator().equals("true")) {
              offsets.add(previousOffset);
            }
          }

          previousOffset += propertyBytes.length;
          first = false;
        }
      }
    }

    int addOffset = 0;
    int resultSize;
    if (mapping.isWithFilesize()) {
      resultSize = byteList.size() + 4;
      addOffset += 4;
    } else {
      resultSize = byteList.size();
    }

    if (mapping.isWithBlockOffsetCreators()) {
      resultSize += offsets.size() * 4;
    }

    byte[] result = new byte[resultSize];

    int targetIndex;
    if (mapping.isWithFilesize()) {
      targetIndex = 4;
    } else {
      targetIndex = 0;
    }

    int offsetFixed = offsets.size() * 4 + addOffset;
    for (Integer offset : offsets) {
      int offsetValue = offset + offsetFixed;
      byte[] offsetBytes = binaryUtils.createIntegerBytes(offsetValue);
      binaryUtils.copyIntoArray(offsetBytes, result, targetIndex);
      targetIndex += 4;
    }

    for (Byte b : byteList) {
      result[targetIndex++] = b.byteValue();
    }

    logger.debug("Done with dynamically mapping {} bytes to {}", result.length, mapping.getClassName());

    return result;
  }

  public byte[] toBinary(Object source, Mapping mapping) {
    logger.debug("Entering mapping " + mapping.getClassName());

    byte[] result = new byte[mapping.getSize()];

    int offset = 0;
    for (MappingProperty property : mapping.getMappingProperties()) {
      List<byte[]> propertyByteList = handleProperty(source, mapping, property);
      for (byte[] propertyBytes : propertyByteList) {
        binaryUtils.copyIntoArray(propertyBytes, result, offset);
        offset += propertyBytes.length;
      }
    }

    logger.debug("Done with statically mapping {} bytes to {}", mapping.getSize(), mapping.getClassName());
    return result;
  }

  private List<byte[]> handleProperty(Object source, Mapping mapping, MappingProperty property) {
    String fieldName = getFieldName(property);
    Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());

    Field field = binaryUtils.getField(targetClass, fieldName);
    Class<?> fieldType = field.getType();
    String fieldTypeName = getFieldTypeName(fieldType);
    Object value = binaryUtils.getValue(source, field);
    int times = property.getTimes();
    int offset = property.getOffset();
    if (offset < 0) {
      offset = 0;
    }

    boolean propertyIsArray = property.getProperty().endsWith("[]");

    List<byte[]> resultList = new ArrayList<>();

    byte[] result;
    if (property instanceof StringMappingProperty) {

      StringMappingProperty smp = (StringMappingProperty) property;
      int propLength = smp.getLength();
      result = binaryUtils.createNullTerminatedFromString((String) value, propLength);
      resultList.add(result);

    } else if (property instanceof SubMappingProperty) {
      SubMappingProperty smp = (SubMappingProperty) property;

      String subFieldName = smp.getProperty().replace("[]", "");
      Field subField = binaryUtils.getField(targetClass, subFieldName);

      if (propertyIsArray) {
        Object[] array = (Object[]) value;
        for (int time = 0; time < times; ++time) {
          Object sub = array[time];
          byte[] bytes;
          if (smp.getSubMapping().getSize() < 0) {
            bytes = toDynamicBinary(sub, smp.getSubMapping());
          } else {
            bytes = toBinary(sub, smp.getSubMapping());
          }
          resultList.add(bytes);
          offset += bytes.length;
        }

      } else {

        Object sub = binaryUtils.getValue(source, subField);
        if (smp.getSubMapping().getSize() < 0) {
          result = toDynamicBinary(sub, smp.getSubMapping());
        } else {
          result = toBinary(sub, smp.getSubMapping());
        }
        resultList.add(result);

      }

    } else {
      if (fieldTypeName.equals("byte")) {

        if (propertyIsArray) {
          byte[] array = (byte[]) value;
          result = new byte[property.getTimes()];
          for (int time = 0; time < property.getTimes(); ++time) {
            result[time] = array[time];
            ++offset;
          }
        } else {
          result = new byte[] { ((Byte) value) };
        }
        resultList.add(result);

      } else if (fieldTypeName.equals("short")) {

        if (propertyIsArray) {
          short[] array;
          if (value != null) {
            array = (short[]) value;
          } else {
            array = new short[times];
            Arrays.fill(array, (short) 0);
          }
          for (int time = 0; time < times; ++time) {
            byte[] bytes = binaryUtils.createShortBytes(array[time]);
            resultList.add(bytes);
            offset += 2;
          }
        } else {
          result = binaryUtils.createShortBytes((Short) value);
          resultList.add(result);
        }

      } else if (fieldTypeName.equals("int")) {

        if (propertyIsArray) {
          int[] array = (int[]) value;
          for (int time = 0; time < times; ++time) {
            byte[] bytes = binaryUtils.createIntegerBytes(array[time]);
            resultList.add(bytes);
            offset += 4;
          }
        } else {
          result = binaryUtils.createIntegerBytes((Integer) value);
          resultList.add(result);
        }

      } else {
        throw new RuntimeException("Unsupported property: " + property.getProperty());
      }
    }

    return resultList;
  }

  private String getFieldTypeName(Class<?> fieldType) {
    String fieldTypeName;
    if (fieldType.isArray()) {
      fieldTypeName = fieldType.getComponentType().getName();
    } else {
      fieldTypeName = fieldType.getName();
    }
    return fieldTypeName;
  }

  private String getFieldName(MappingProperty property) {
    String fieldName = property.getProperty();
    if (fieldName.endsWith("[]")) {
      fieldName = fieldName.replace("[]", "");
    }
    return fieldName;
  }

  public static BinaryWriter getInstance() {
    return instance;
  }
}
