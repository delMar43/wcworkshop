package binmap;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryMapper {
  private static final Logger logger = LoggerFactory.getLogger(BinaryMapper.class);

  public <T> T toJava(byte[] data, Mapping mapping, Class<T> targetClass) {
    T t = instantiate(targetClass);

    fillWithData(t, data, mapping, targetClass);

    return t;
  }

  private void fillWithData(Object sink, byte[] data, Mapping mapping, Class<?> targetClass) {
    for (MappingProperty property : mapping.getMappingProperties()) {
      Field field = getField(targetClass, property.getProperty());
      Class<?> type = field.getType();
      if ("java.lang.String".equals(type.getName())) {
        StringMappingProperty smp = (StringMappingProperty) property;
        String value = new String(Arrays.copyOfRange(data, smp.getOffset(), smp.getLength()));
        if (value.contains("\0")) {
          value = value.substring(0, value.indexOf("\0"));
        }
        setValue(sink, field, value);
        System.out.println("string is " + value);
      } else if ("byte".equals(type.getName())) {
        byte value = data[property.getOffset()];
        setValue(sink, field, value);
      } else {
        System.out.println("type: " + type);
      }
    }
  }

  private void setValue(Object sink, Field field, Object value) {
    try {
      boolean accessible = field.isAccessible();
      if (!accessible) {
        field.setAccessible(true);
      }
      field.set(sink, value);
      if (!accessible) {
        field.setAccessible(false);
      }
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e.getMessage(), e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private Field getField(Class<?> clazz, String fieldName) {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Field '" + fieldName + "' not found in class " + clazz.getName());
    } catch (SecurityException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private <T> T instantiate(Class<T> clazz) {
    T result;
    try {
      result = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Unable to instanciate " + clazz.getName() + " because of " + e.getMessage());
    }

    return result;
  }
}
