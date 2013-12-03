package binmap;

import java.lang.reflect.Field;

public class BinaryWriter {
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  public byte[] toBinary(Object source, Mapping mapping) {
    byte[] result = new byte[mapping.getSize()];

    int curTargetOffset = 0;
    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName = property.getProperty();
      if (fieldName.endsWith("[]")) {
        fieldName = fieldName.replace("[]", "");
      }
      Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());

      Field field = binaryUtils.getField(targetClass, fieldName);
      String fieldTypeName = field.getType().getName();
      Object value = binaryUtils.getValue(source, field);

      if (property instanceof StringMappingProperty) {
        StringMappingProperty smp = (StringMappingProperty) property;
        int propLength = smp.getLength();
        byte[] bytes = binaryUtils.createNullTerminatedFromString((String) value, propLength);
        binaryUtils.copyIntoArray(bytes, result, curTargetOffset);
        System.out.println();
        curTargetOffset += propLength;
      } else if (property instanceof SubMappingProperty) {
        SubMappingProperty smp = (SubMappingProperty) property;
        System.out.println("sub: " + fieldName);
        curTargetOffset += smp.getSubMapping().getSize();
      } else {
        if (fieldTypeName.equals("byte")) {
          result[curTargetOffset++] = ((Byte) value);
        } else if (fieldTypeName.equals("short")) {
          byte[] bytes = binaryUtils.createShortBytes((Short) value);
          binaryUtils.copyIntoArray(bytes, result, curTargetOffset);
          curTargetOffset += 2;
        } else if (fieldTypeName.equals("integer")) {
          byte[] bytes = binaryUtils.createIntegerBytes((Integer) value);
          binaryUtils.copyIntoArray(bytes, result, curTargetOffset);
          curTargetOffset += 4;
        } else {
          throw new RuntimeException("Unsupported type: " + fieldTypeName);
        }
      }

      System.out.println(fieldName + ": " + value);
    }
    return result;
  }
}
