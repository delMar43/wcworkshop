package binmap;

public class MappingProperty {

  private String property;
  private int offset;

  public MappingProperty(String property, int offset) {
    this.property = property;
    this.offset = offset;
  }

  public String getProperty() {
    return property;
  }

  public int getOffset() {
    return offset;
  }
}
