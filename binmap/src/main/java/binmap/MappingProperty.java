package binmap;

public class MappingProperty {

  private String property;
  private int offset;
  private int times;

  public MappingProperty(String property, int offset, int times) {
    this.property = property;
    this.offset = offset;
    this.times = times;
  }

  public String getProperty() {
    return property;
  }

  public int getOffset() {
    return offset;
  }

  public int getTimes() {
    return times;
  }
}
