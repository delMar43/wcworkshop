package binmap;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryWriter {
  private static final Logger logger = LoggerFactory.getLogger(BinaryWriter.class);
  private static final BinaryWriter instance = new BinaryWriter();
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  private BinaryWriter() {
  }

  public byte[] toBinary(Object source, Mapping mapping) {
    logger.debug("Entering mapping " + mapping.getClassName());

    byte[] result = new byte[mapping.getSize()];

    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName = property.getProperty();
      if (fieldName.endsWith("[]")) {
        fieldName = fieldName.replace("[]", "");
      }
      Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());

      Field field = binaryUtils.getField(targetClass, fieldName);
      Class<?> fieldType = field.getType();
      String fieldTypeName;
      if (fieldType.isArray()) {
        fieldTypeName = fieldType.getComponentType().getName();
      } else {
        fieldTypeName = fieldType.getName();
      }
      Object value = binaryUtils.getValue(source, field);
      int times = property.getTimes();
      int offset = property.getOffset();

      if (property instanceof StringMappingProperty) {

        StringMappingProperty smp = (StringMappingProperty) property;
        int propLength = smp.getLength();
        byte[] bytes = binaryUtils.createNullTerminatedFromString((String) value, propLength);
        binaryUtils.copyIntoArray(bytes, result, offset);

      } else if (property instanceof SubMappingProperty) {
        SubMappingProperty smp = (SubMappingProperty) property;

        String subFieldName = smp.getProperty().replace("[]", "");
        Field subField = binaryUtils.getField(targetClass, subFieldName);

        if (times > 1) {

          Object[] array = (Object[]) value;
          for (int time = 0; time < times; ++time) {
            Object sub = array[time];
            byte[] bytes = toBinary(sub, smp.getSubMapping());
            binaryUtils.copyIntoArray(bytes, result, offset);
            offset += smp.getSubMapping().getSize();
          }

        } else {

          Object sub = binaryUtils.getValue(source, subField);
          byte[] bytes = toBinary(sub, smp.getSubMapping());
          binaryUtils.copyIntoArray(bytes, result, offset);

        }

      } else {
        if (fieldTypeName.equals("byte")) {

          if (times > 1) {
            byte[] array = (byte[]) value;
            for (int time = 0; time < property.getTimes(); ++time) {
              result[offset] = array[time];
              ++offset;
            }
          } else {
            result[offset] = ((Byte) value);
          }

        } else if (fieldTypeName.equals("short")) {

          if (times > 1) {
            short[] array;
            if (value != null) {
              array = (short[]) value;
            } else {
              array = new short[times];
              Arrays.fill(array, (short) 0);
            }
            for (int time = 0; time < times; ++time) {
              byte[] bytes = binaryUtils.createShortBytes(array[time]);
              binaryUtils.copyIntoArray(bytes, result, offset);
              offset += 2;
            }
          } else {
            byte[] bytes = binaryUtils.createShortBytes((Short) value);
            binaryUtils.copyIntoArray(bytes, result, offset);
          }

        } else if (fieldTypeName.equals("int")) {

          if (times > 1) {
            int[] array = (int[]) value;
            for (int time = 0; time < times; ++time) {
              byte[] bytes = binaryUtils.createIntegerBytes(array[time]);
              binaryUtils.copyIntoArray(bytes, result, offset);
              offset += 4;
            }
          } else {
            byte[] bytes = binaryUtils.createIntegerBytes((Integer) value);
            binaryUtils.copyIntoArray(bytes, result, offset);
          }

        } else {
          throw new RuntimeException("Unsupported type: " + fieldTypeName);
        }
      }

    }
    logger.debug("Done with mapping " + mapping.getClassName());
    return result;
  }

  public static BinaryWriter getInstance() {
    return instance;
  }
}
