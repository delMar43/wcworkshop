package binmap;

import java.lang.reflect.Field;

public class BinaryWriter {
  private static final BinaryWriter instance = new BinaryWriter();
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  private BinaryWriter() {
  }

  public byte[] toBinary(Object source, Mapping mapping) {
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
        System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
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
            System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
            offset += smp.getSubMapping().getSize();
          }

        } else {

          Object sub = binaryUtils.getValue(source, subField);
          byte[] bytes = toBinary(sub, smp.getSubMapping());
          binaryUtils.copyIntoArray(bytes, result, offset);
          System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));

        }

      } else {
        if (fieldTypeName.equals("byte")) {

          if (times > 1) {
            byte[] array = (byte[]) value;
            for (int time = 0; time < property.getTimes(); ++time) {
              result[offset] = array[time];
              System.out.println("[" + offset + "] " + property.getProperty() + ": " + Integer.toHexString((Byte) value));
              ++offset;
            }
          } else {
            result[offset] = ((Byte) value);
            System.out.println("[" + offset + "] " + property.getProperty() + ": " + Integer.toHexString((Byte) value));
          }

        } else if (fieldTypeName.equals("short")) {

          if (times > 1) {
            short[] array = (short[]) value;
            for (int time = 0; time < times; ++time) {
              byte[] bytes = binaryUtils.createShortBytes(array[time]);
              System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
              binaryUtils.copyIntoArray(bytes, result, offset);
              offset += 2;
            }
          } else {
            byte[] bytes = binaryUtils.createShortBytes((Short) value);
            System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
            binaryUtils.copyIntoArray(bytes, result, offset);
          }

        } else if (fieldTypeName.equals("integer")) {

          if (times > 1) {
            int[] array = (int[]) value;
            for (int time = 0; time < times; ++time) {
              byte[] bytes = binaryUtils.createIntegerBytes(array[time]);
              System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
              binaryUtils.copyIntoArray(bytes, result, offset);
              offset += 4;
            }
          } else {
            byte[] bytes = binaryUtils.createIntegerBytes((Integer) value);
            System.out.println("[" + offset + "] " + property.getProperty() + ": " + binaryUtils.byteArrayToHexString(bytes));
            binaryUtils.copyIntoArray(bytes, result, offset);
          }

        } else {
          throw new RuntimeException("Unsupported type: " + fieldTypeName);
        }
      }

    }
    return result;
  }

  public static BinaryWriter getInstance() {
    return instance;
  }
}
