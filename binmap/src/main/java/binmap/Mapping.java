package binmap;

import java.util.List;
import java.util.Map;

public class Mapping {
  private String className;
  private int size;
  private List<MappingProperty> mappingProperties;
  private Map<String, String> dynamicOffsets;

  public Mapping(String className, int size, List<MappingProperty> mappingProperties, Map<String, String> dynamicOffsets) {
    this.className = className;
    this.size = size;
    this.mappingProperties = mappingProperties;
    this.dynamicOffsets = dynamicOffsets;
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

  public boolean isWithDynamicOffsets() {
    return dynamicOffsets != null && !dynamicOffsets.isEmpty();
  }

  public String getDynamicOffset(String propertyName) {
    if (dynamicOffsets.containsKey(propertyName)) {
      return dynamicOffsets.get(propertyName);
    } else {
      throw new RuntimeException("No dynamic offset for property " + propertyName + " of class " + className + " found!");
    }
  }

}
