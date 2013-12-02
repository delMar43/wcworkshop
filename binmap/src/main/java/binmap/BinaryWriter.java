package binmap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BinaryWriter {
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  public Byte[] toBinary(Object source, Mapping mapping) {
    List<Byte> result = new ArrayList<>();
    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName = property.getProperty();
      if (fieldName.endsWith("[]")) {
        fieldName = fieldName.replace("[]", "");
      }
      Class<?> targetClass = binaryUtils.getClass(mapping.getClassName());

      Field field = binaryUtils.getField(targetClass, fieldName);
      Object value = binaryUtils.getValue(source, field);
      System.out.println(fieldName + ": " + value);
    }
    return result.toArray(new Byte[] {});
  }
}
