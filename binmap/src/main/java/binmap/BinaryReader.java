package binmap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryReader {
  private static final BinaryReader instance = new BinaryReader();
  private static final Logger logger = LoggerFactory.getLogger(BinaryReader.class);
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  private BinaryReader() {
  }

  public <T> T toJava(byte[] data, Mapping mapping, Class<T> targetClass) {
    T t = binaryUtils.instantiate(targetClass);

    fillWithData(t, data, mapping, targetClass);

    return t;
  }

  private void fillWithData(Object sink, byte[] data, Mapping mapping, Class<?> targetClass) {

    List<Integer> dynamicOffsets;
    List<Integer> dynamicSizes;
    if (mapping.isWithDynamicOffsets()) {
      dynamicOffsets = extractBlockOffsets(data, mapping.getOffsetStart());
      dynamicSizes = new ArrayList<>();
      for (int offsetIdx = 0; offsetIdx < dynamicOffsets.size(); ++offsetIdx) {
        int curSize;
        if (offsetIdx + 1 < dynamicOffsets.size()) {
          curSize = dynamicOffsets.get(offsetIdx + 1) - dynamicOffsets.get(offsetIdx);
        } else {
          curSize = data.length - dynamicOffsets.get(offsetIdx);
        }
        dynamicSizes.add(curSize);
        System.out.print(dynamicOffsets.get(offsetIdx) + " ");
      }
      System.out.println();
    } else {
      dynamicOffsets = Collections.EMPTY_LIST;
      dynamicSizes = Collections.EMPTY_LIST;
    }

    int cummulatedLength = 0;
    for (MappingProperty property : mapping.getMappingProperties()) {
      String fieldName;
      if (property.getProperty().endsWith("[]")) {
        fieldName = property.getProperty().replace("[]", "");
      } else {
        fieldName = property.getProperty();
      }

      Field field = binaryUtils.getField(targetClass, fieldName);
      String typeName;
      Class<?> type = field.getType();
      if (type.isArray()) {
        typeName = type.getComponentType().getName();
      } else {
        typeName = type.getName();
      }

      int propertyOffset = property.getOffset();
      if (propertyOffset == Constants.OFFSET_BLOCK) {
        propertyOffset = getDynamicOffset(mapping, dynamicOffsets, property.getProperty());
      } else if (propertyOffset == Constants.OFFSET_CALC) {
        propertyOffset = cummulatedLength;
      }

      int times = property.getTimes();
      if (times == Constants.TIMES_OFFSETS) {
        times = dynamicOffsets.size();
      } else if (times == Constants.TIMES_TOTHEEND) {
        int curOffsetIndex = getCurrentOffsetIndex(mapping, property.getProperty());
        times = dynamicOffsets.size() - curOffsetIndex;
      }
      if (property instanceof SubMappingProperty) {

        SubMappingProperty smp = (SubMappingProperty) property;
        if (times > 1) {
          Object[] array = (Object[]) Array.newInstance(type.getComponentType(), times);
          int currentOffset = 0;
          for (int idx = 0; idx < times; ++idx) {
            int offsetIndex = getCurrentOffsetIndex(mapping, property.getProperty()) + idx;
            if (idx != 0) {
              currentOffset += getDynamicSize(smp.getSubMapping(), dynamicSizes, offsetIndex - 1);
            }
            int from = currentOffset + propertyOffset;
            int dynSize = getDynamicSize(smp.getSubMapping(), dynamicSizes, offsetIndex);

            if (dynSize > 0) {
              int to = from + dynSize;
              Object subMappingObject = createSubMappingObject(data, smp, from, to);
              Array.set(array, idx, subMappingObject);
              cummulatedLength += smp.getSubMapping().getSize();
            }

          }
          binaryUtils.setValue(sink, field, array);
        } else {
          int from = propertyOffset;
          int to = from + getDynamicSize(smp.getSubMapping(), dynamicSizes, 0);
          Object object = createSubMappingObject(data, smp, from, to);
          binaryUtils.setValue(sink, field, object);
          cummulatedLength += smp.getSubMapping().getSize();
        }

      } else if ("java.lang.String".equals(typeName)) {

        if (property instanceof NullTermStringMappingProperty) {

          if (times > 1) {
            String[] array = (String[]) Array.newInstance(type.getComponentType(), times);
            for (int idx = 0; idx < times; ++idx) {
              int from = propertyOffset;
              String value = getString(data, from);
              Array.set(array, idx, value);
              cummulatedLength += value.length();
            }
            binaryUtils.setValue(sink, field, array);
          } else {
            String value = getString(data, propertyOffset);
            cummulatedLength += value.length();
            binaryUtils.setValue(sink, field, value);
          }
        } else {
          StringMappingProperty smp = (StringMappingProperty) property;

          if (times > 1) {
            String[] array = (String[]) Array.newInstance(type.getComponentType(), times);
            for (int idx = 0; idx < times; ++idx) {
              int propLength = smp.getLength();
              int from = propertyOffset + idx * propLength;
              Array.set(array, idx, getString(data, from, propLength));
            }
            binaryUtils.setValue(sink, field, array);
          } else {
            String value = getString(data, propertyOffset, smp.getLength());
            binaryUtils.setValue(sink, field, value);
          }
        }

      } else if ("byte".equals(typeName)) {

        if (times > 1) {
          byte[] array = (byte[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int pos = propertyOffset + idx;
            Array.set(array, idx, data[pos]);
            ++cummulatedLength;
          }
          binaryUtils.setValue(sink, field, array);
        } else {
          byte value = data[propertyOffset];
          binaryUtils.setValue(sink, field, value);
          ++cummulatedLength;
        }

      } else if ("short".equals(typeName)) {
        if (times > 1) {
          short[] array = (short[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int from = propertyOffset + 2 * idx;
            int to = from + 2;
            byte[] bytes = Arrays.copyOfRange(data, from, to);
            short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
            Array.set(array, idx, value);
            cummulatedLength += 2;
          }
          binaryUtils.setValue(sink, field, array);
        } else {
          int from = propertyOffset;
          int to = from + 2;
          byte[] bytes = Arrays.copyOfRange(data, from, to);
          short value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
          binaryUtils.setValue(sink, field, value);
          cummulatedLength += 2;
        }

      } else if ("int".equals(typeName)) {

        IntegerMappingProperty imp = (IntegerMappingProperty) property;

        if (times > 1) {
          int[] array = (int[]) Array.newInstance(type.getComponentType(), times);
          for (int idx = 0; idx < times; ++idx) {
            int from = propertyOffset + 4 * idx;
            int to = from + 4;
            byte[] bytes = Arrays.copyOfRange(data, from, to);
            if (imp.isOnlyThreeBytes()) {
              bytes[3] = 0;
            }
            int value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
            Array.set(array, idx, value);
            cummulatedLength += 4;
          }
          binaryUtils.setValue(sink, field, array);
        } else {
          int from = propertyOffset;
          int to = from + 4;
          byte[] bytes = Arrays.copyOfRange(data, from, to);
          if (imp.isOnlyThreeBytes()) {
            bytes[3] = 0;
          }
          int value = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
          binaryUtils.setValue(sink, field, value);
          cummulatedLength += 4;
        }

      } else {
        throw new RuntimeException("Unable to handle type " + typeName);
      }
    }
  }

  private int getDynamicOffset(Mapping mapping, List<Integer> dynamicOffsets, String propertyName) {
    int propertyOffset;
    int offsetIndex = getCurrentOffsetIndex(mapping, propertyName);
    propertyOffset = dynamicOffsets.get(offsetIndex);
    return propertyOffset;
  }

  private int getCurrentOffsetIndex(Mapping mapping, String propertyName) {
    String propKey = mapping.getDynamicOffset(propertyName);
    int offsetIndex = Integer.parseInt(propKey.replace("{", "").replace("}", ""));
    return offsetIndex;
  }

  private int getDynamicSize(Mapping mapping, List<Integer> dynamicSizes, int index) {
    int size = mapping.getSize();
    if (size == Constants.SIZE_DYNAMIC) {
      size = dynamicSizes.get(index);
    }
    return size;
  }

  private List<Integer> extractBlockOffsets(byte[] buffer, int offsetStart) {
    List<Integer> blockOffsets = new ArrayList<>();
    int offset = offsetStart;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      bytes[3] = 0; //
      blockOffsets.add(binaryUtils.getInteger(bytes));
      offset += 4;
    } while (offset < blockOffsets.get(0));

    return blockOffsets;
  }

  private Object createSubMappingObject(byte[] data, SubMappingProperty smp, int from, int to) {
    logger.debug("createSubMappingObject for {} from {} to {} inside data of size {}", new Object[] { smp.getProperty(), from, to, data.length });
    byte[] subData = Arrays.copyOfRange(data, from, to);
    Class<?> clazz = binaryUtils.getClass(smp.getSubMapping().getClassName());
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

  private String getString(byte[] block, int offset) {

    int endIndex = -1;
    for (int idx = offset; idx < block.length; ++idx) {
      if (block[idx] == (byte) 0) {
        endIndex = idx;
        break;
      }
    }

    String result;
    int length;
    if (endIndex == -1) {
      length = block.length - offset;
    } else {
      length = endIndex - offset;
    }
    result = getString(block, offset, length);
    return result;
  }

  public static BinaryReader getInstance() {
    return instance;
  }
}
