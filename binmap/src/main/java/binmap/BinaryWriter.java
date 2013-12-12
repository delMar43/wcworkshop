package binmap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName = getFieldName(property);
      Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());
      Field field = binaryUtils.getField(targetClass, fieldName);
      Class<?> fieldType = field.getType();
      String fieldTypeName = getFieldTypeName(fieldType);

      Object value = binaryUtils.getValue(source, field);
      if (value == null) {
        System.out.println("NULL OBJECT !!!");
        continue;
      }
      boolean propertyIsArray = property.getProperty().endsWith("[]");
      int times;
      if (propertyIsArray) {
        times = Array.getLength(value);
      } else {
        times = 1;
      }

      MappingProperty dynamicProperty;
      if (property instanceof SubMappingProperty) {

        Mapping subMapping = ((SubMappingProperty) property).getSubMapping();
        dynamicProperty = new SubMappingProperty(property.getProperty(), property.getOffset(), subMapping, times);

      } else if (property instanceof StringMappingProperty) {

        dynamicProperty = new StringMappingProperty(property.getProperty(), property.getOffset(),
            ((StringMappingProperty) property).getLength(), property.getTimes());

      } else {

        dynamicProperty = new MappingProperty(property.getProperty(), property.getOffset(), times);

      }

      byte[] propertyBytes = handleProperty(source, mapping, dynamicProperty);
      for (byte b : propertyBytes) {
        byteList.add(b);
      }
    }

    byte[] result = new byte[byteList.size()];
    int idx = 0;
    for (Byte b : byteList) {
      result[idx++] = b.byteValue();
    }

    logger.debug("Done with dynamically mapping {} bytes to {}", result.length, mapping.getClassName());

    return result;
  }

  public byte[] toBinary(Object source, Mapping mapping) {
    logger.debug("Entering mapping " + mapping.getClassName());

    byte[] result = new byte[mapping.getSize()];

    int offset = 0;
    for (MappingProperty property : mapping.getMappingProperties()) {
      byte[] propertyBytes = handleProperty(source, mapping, property);
      binaryUtils.copyIntoArray(propertyBytes, result, offset);
      offset += propertyBytes.length;
    }

    logger.debug("Done with statically mapping {} bytes to {}", mapping.getSize(), mapping.getClassName());
    return result;
  }

  private byte[] handleProperty(Object source, Mapping mapping, MappingProperty property) {
    String fieldName = getFieldName(property);
    Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());

    Field field = binaryUtils.getField(targetClass, fieldName);
    Class<?> fieldType = field.getType();
    String fieldTypeName = getFieldTypeName(fieldType);
    Object value = binaryUtils.getValue(source, field);
    int times = property.getTimes();
    int offset = property.getOffset();

    boolean propertyIsArray = property.getProperty().endsWith("[]");

    byte[] result;
    if (property instanceof StringMappingProperty) {

      StringMappingProperty smp = (StringMappingProperty) property;
      int propLength = smp.getLength();
      result = binaryUtils.createNullTerminatedFromString((String) value, propLength);
      //      binaryUtils.copyIntoArray(bytes, result, offset);

    } else if (property instanceof SubMappingProperty) {
      SubMappingProperty smp = (SubMappingProperty) property;

      String subFieldName = smp.getProperty().replace("[]", "");
      Field subField = binaryUtils.getField(targetClass, subFieldName);

      if (propertyIsArray) {
        List<Byte> tempList = new ArrayList<>();
        Object[] array = (Object[]) value;
        for (int time = 0; time < times; ++time) {
          Object sub = array[time];
          byte[] bytes;
          if (smp.getSubMapping().getSize() < 0) {
            bytes = toDynamicBinary(sub, smp.getSubMapping());
          } else {
            bytes = toBinary(sub, smp.getSubMapping());
          }
          for (byte b : bytes) {
            tempList.add(b);
          }
          //          binaryUtils.copyIntoArray(bytes, result, offset);
          offset += smp.getSubMapping().getSize();
        }

        result = new byte[tempList.size()];
        int idx = 0;
        for (Byte b : tempList) {
          result[idx++] = b.byteValue();
        }

      } else {

        Object sub = binaryUtils.getValue(source, subField);
        if (smp.getSubMapping().getSize() < 0) {
          result = toDynamicBinary(sub, smp.getSubMapping());
        } else {
          result = toBinary(sub, smp.getSubMapping());
        }
        //        binaryUtils.copyIntoArray(bytes, result, offset);

      }

    } else {
      if (fieldTypeName.equals("byte")) {

        if (propertyIsArray) {
          byte[] array = (byte[]) value;
          result = new byte[property.getTimes()];
          for (int time = 0; time < property.getTimes(); ++time) {
            result[offset] = array[time];
            ++offset;
          }
        } else {
          result = new byte[] { ((Byte) value) };
        }

      } else if (fieldTypeName.equals("short")) {

        if (propertyIsArray) {
          short[] array;
          if (value != null) {
            array = (short[]) value;
          } else {
            array = new short[times];
            Arrays.fill(array, (short) 0);
          }
          result = new byte[times * 2];
          for (int time = 0; time < times; ++time) {
            byte[] bytes = binaryUtils.createShortBytes(array[time]);
            binaryUtils.copyIntoArray(bytes, result, time * 2);
            //            binaryUtils.copyIntoArray(bytes, result, offset);
            offset += 2;
          }
        } else {
          result = binaryUtils.createShortBytes((Short) value);
          //          binaryUtils.copyIntoArray(bytes, result, offset);
        }

      } else if (fieldTypeName.equals("int")) {

        if (propertyIsArray) {
          int[] array = (int[]) value;
          result = new byte[times * 4];
          for (int time = 0; time < times; ++time) {
            byte[] bytes = binaryUtils.createIntegerBytes(array[time]);
            binaryUtils.copyIntoArray(bytes, result, time * 4);
            //            binaryUtils.copyIntoArray(bytes, result, offset);
            offset += 4;
          }
        } else {
          result = binaryUtils.createIntegerBytes((Integer) value);
          //          binaryUtils.copyIntoArray(bytes, result, offset);
        }

      } else {
        throw new RuntimeException("Unsupported property: " + property.getProperty());
      }
    }

    return result;
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
