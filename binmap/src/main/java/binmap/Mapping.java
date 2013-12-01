package binmap;

import java.util.List;

public class Mapping {
  private String className;
  private int size;
  private List<MappingProperty> mappingProperties;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public List<MappingProperty> getMappingProperties() {
    return mappingProperties;
  }

  public void setMappingProperties(List<MappingProperty> mappingProperties) {
    this.mappingProperties = mappingProperties;
  }

}
