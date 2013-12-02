package binmap;

import java.lang.reflect.Field;

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

  public static BinaryUtils getInstance() {
    return instance;
  }
}
