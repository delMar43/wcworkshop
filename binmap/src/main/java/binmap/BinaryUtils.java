package binmap;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryUtils {
  private static final BinaryUtils instance = new BinaryUtils();

  public void setValue(Object sink, Field field, Object value) {
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

  public Object getValue(Object source, Field field) {
    try {
      boolean accessible = field.isAccessible();
      if (!accessible) {
        field.setAccessible(true);
      }

      Object result = field.get(source);

      if (!accessible) {
        field.setAccessible(false);
      }

      return result;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Field getField(Class<?> clazz, String fieldName) {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("Field '" + fieldName + "' not found in class " + clazz.getName());
    } catch (SecurityException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public <T> T instantiate(Class<T> clazz) {
    T result;
    try {
      result = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Unable to instanciate " + clazz.getName() + " because of " + e.getMessage());
    }

    return result;
  }

  public Class<?> getClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to get class for name '" + className + "'");
    }
  }

  public byte[] createNullTerminatedFromString(String value, int resultLength) {
    byte[] result = new byte[resultLength];
    byte[] bytes = value.getBytes();
    for (int idx = 0; idx < bytes.length; ++idx) {
      result[idx] = bytes[idx];
    }
    if (bytes.length < resultLength) {
      for (int idx = bytes.length; idx < resultLength; ++idx) {
        result[idx] = 0;
      }
    }

    return result;
  }

  public void copyIntoArray(byte[] source, byte[] sink, int targetOffset) {
    for (int idx = 0; idx < source.length; ++idx) {
      sink[idx + targetOffset] = source[idx];
    }
  }

  public byte[] createIntegerBytes(int value) {
    ByteBuffer record = createLittleEndianByteBuffer();
    record.putInt(value);
    return record.array();
  }

  public byte[] createShortBytes(short value) {
    ByteBuffer record = createLittleEndianByteBuffer();
    record.putShort(value);
    return record.array();
  }

  private ByteBuffer createLittleEndianByteBuffer() {
    ByteBuffer record = ByteBuffer.allocate(2);
    record.order(ByteOrder.LITTLE_ENDIAN);
    return record;
  }

  public String byteArrayToHexString(byte[] input) {
    if (input == null) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (byte b : input) {
      result.append("0x" + Integer.toHexString(b) + " ");
    }
    return result.toString();
  }

  public static BinaryUtils getInstance() {
    return instance;
  }
}
