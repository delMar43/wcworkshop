package binmap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
      String fieldName;
      if (property.getProperty().endsWith("[]")) {
        fieldName = property.getProperty().replace("[]", "");
      } else {
        fieldName = property.getProperty();
      }

      Field field = getField(targetClass, fieldName);
      String typeName;
      Class<?> type = field.getType();
      if (type.isArray()) {
        typeName = type.getComponentType().getName();
      } else {
        typeName = type.getName();
      }

      int times = property.getTimes();
      if (property instanceof SubMappingProperty) {

        if (times > 1) {
          Object[] array = (Object[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            Object subMappingObject = createSubMappingObject(data, property);
            Array.set(array, idx, subMappingObject);
            // array[idx] = subMappingObject;
          }
          setValue(sink, field, array);
        } else {
          Object object = createSubMappingObject(data, property);
          setValue(sink, field, object);
        }

      } else if ("java.lang.String".equals(typeName)) {
        StringMappingProperty smp = (StringMappingProperty) property;

        if (times > 1) {
          String[] array = (String[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            Array.set(array, idx, getString(data, smp.getOffset(), smp.getLength()));
            // array[idx] = getString(data, smp.getOffset(), smp.getLength());
          }
          setValue(sink, field, array);
        } else {
          String value = getString(data, smp.getOffset(), smp.getLength());
          setValue(sink, field, value);
        }

      } else if ("byte".equals(typeName)) {

        if (times > 1) {
          byte[] array = (byte[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            Array.set(array, idx, data[property.getOffset()]);
            // array[idx] = data[property.getOffset()];
          }
          setValue(sink, field, array);
        } else {
          byte value = data[property.getOffset()];
          setValue(sink, field, value);
        }

      } else if ("short".equals(typeName)) {
        if (times > 1) {
          short[] array = (short[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            byte[] bytes = Arrays.copyOfRange(data, property.getOffset(), property.getOffset() + 2);
            short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
            Array.set(array, idx, value);
            array[idx] = value;
          }
          setValue(sink, field, array);
        } else {
          byte[] bytes = Arrays.copyOfRange(data, property.getOffset(), property.getOffset() + 2);
          short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
          setValue(sink, field, value);
        }

      } else {
        System.out.println("type: " + type);
      }
    }
  }

  private Object createSubMappingObject(byte[] data, MappingProperty property) {
    SubMappingProperty smp = (SubMappingProperty) property;
    byte[] subData = Arrays.copyOfRange(data, smp.getOffset(), smp.getOffset() + smp.getSubMapping().getSize());
    Class<?> clazz = getClass(smp.getSubMapping().getClassName());
    Object sub = toJava(subData, smp.getSubMapping(), clazz);
    return sub;
  }

  private String getString(byte[] block, int offset, int length) {
    String result = new String(block, offset, length);
    if (result.contains("\0")) {
      result = result.substring(0, result.indexOf("\0"));
    }
    return result;
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

  private Class<?> getClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to get class for name '" + className + "'");
    }
  }
}
