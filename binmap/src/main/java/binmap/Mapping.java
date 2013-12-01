package binmap;

import java.util.List;

public class Mapping {
  private String className;
  private int size;
  private List<MappingProperty> mappingProperties;

  public Mapping(String className, int size, List<MappingProperty> mappingProperties) {
    this.className = className;
    this.size = size;
    this.mappingProperties = mappingProperties;
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

}
