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
    return toJava(data, mapping, 0, targetClass);
  }

  public <T> T toJava(byte[] data, Mapping mapping, int offset, Class<T> targetClass) {
    T t = instantiate(targetClass);

    fillWithData(t, data, mapping, offset, targetClass);

    return t;
  }

  private void fillWithData(Object sink, byte[] data, Mapping mapping, int globalOffset, Class<?> targetClass) {
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

        SubMappingProperty smp = (SubMappingProperty) property;
        if (times > 1) {
          Object[] array = (Object[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int fixedOffset = idx * smp.getSubMapping().getSize();
            Object subMappingObject = createSubMappingObject(data, smp, fixedOffset, globalOffset);
            Array.set(array, idx, subMappingObject);
          }
          setValue(sink, field, array);
        } else {
          Object object = createSubMappingObject(data, smp, 0, globalOffset);
          setValue(sink, field, object);
        }

      } else if ("java.lang.String".equals(typeName)) {
        StringMappingProperty smp = (StringMappingProperty) property;

        if (times > 1) {
          String[] array = (String[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int from = globalOffset + smp.getOffset();
            int to = from + smp.getLength();
            Array.set(array, idx, getString(data, from, to));
          }
          setValue(sink, field, array);
        } else {
          String value = getString(data, globalOffset + smp.getOffset(), smp.getLength());
          setValue(sink, field, value);
        }

      } else if ("byte".equals(typeName)) {

        if (times > 1) {
          byte[] array = (byte[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int pos = globalOffset + property.getOffset() * idx;
            Array.set(array, idx, data[pos]);
          }
          setValue(sink, field, array);
        } else {
          byte value = data[globalOffset + property.getOffset()];
          setValue(sink, field, value);
        }

      } else if ("short".equals(typeName)) {
        if (times > 1) {
          short[] array = (short[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int from = globalOffset + property.getOffset() + 2 * idx;
            int to = from + 2;
            byte[] bytes = Arrays.copyOfRange(data, from, to);
            short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
            Array.set(array, idx, value);
          }
          setValue(sink, field, array);
        } else {
          int from = globalOffset + property.getOffset();
          int to = from + 2;
          byte[] bytes = Arrays.copyOfRange(data, from, to);
          short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
          setValue(sink, field, value);
        }

      } else {
        throw new RuntimeException("Unable to handle type " + typeName);
      }
    }
  }

  private Object createSubMappingObject(byte[] data, SubMappingProperty smp, int fixedOffset, int globalOffset) {
    int from = globalOffset + fixedOffset + smp.getOffset();
    int to = from + smp.getSubMapping().getSize();
    byte[] subData = Arrays.copyOfRange(data, from, to);
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
