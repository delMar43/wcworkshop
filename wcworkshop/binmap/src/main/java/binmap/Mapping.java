package binmap;

import java.util.List;
import java.util.Map;

public class Mapping {
  private String mappingFilename;
  private String className;
  private int size;
  private List<MappingProperty> mappingProperties;
  private Map<String, String> dynamicOffsets;
  private boolean withFilesize;
  private Integer offsetStart;

  public Mapping(String mappingFilename, String className, int size, List<MappingProperty> mappingProperties,
      Map<String, String> dynamicOffsets, boolean withFilesize, Integer offsetStart) {
    this.mappingFilename = mappingFilename;
    this.className = className;
    this.size = size;
    this.mappingProperties = mappingProperties;
    this.dynamicOffsets = dynamicOffsets;
    this.withFilesize = withFilesize;
    this.offsetStart = offsetStart;
  }

  @Override
  public String toString() {
    return "map=" + mappingFilename + ",class=" + className + ",size=" + size + ",withFilesize=" + withFilesize + ",offsetStart="
        + offsetStart;
  }

  public String getClassName() {
    return className;
  }

  public int getSize() {
    return size;
  }

  public List<MappingProperty> getMappingProperties() {
    return mappingProperties;
  }

  public Map<String, String> getDynamicOffsets() {
    return dynamicOffsets;
  }

  public Integer getOffsetStart() {
    return offsetStart;
  }

  public boolean hasOffsets() {
    return offsetStart != null && offsetStart >= 0;
  }

  public boolean isWithDynamicOffsets() {
    return dynamicOffsets != null && !dynamicOffsets.isEmpty();
  }

  public boolean isWithFilesize() {
    return withFilesize;
  }

  public String getDynamicOffset(String propertyName) {
    if (dynamicOffsets.containsKey(propertyName)) {
      return dynamicOffsets.get(propertyName);
    } else {
      throw new RuntimeException("No dynamic offset for property " + propertyName + " of class " + className + " found!");
    }
  }

  public boolean isWithBlockOffsetCreators() {
    for (MappingProperty prop : mappingProperties) {
      if (prop.isBlockOffsetCreator() != null) {
        return true;
      }
    }
    return false;
  }

  public String getMappingFilename() {
    return mappingFilename;
  }
}
