package binmap;

public class MappingProperty {

  private String property;
  private int offset;
  private int times;
  private boolean blockOffsetCreator;

  public MappingProperty(String property, int offset, int times, boolean blockOffsetCreator) {
    this.property = property;
    this.offset = offset;
    this.times = times;
    this.blockOffsetCreator = blockOffsetCreator;
  }

  @Override
  public String toString() {
    return "property=" + property + ",offset=" + offset + ",times=" + times;
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

  public boolean isBlockOffsetCreator() {
    return blockOffsetCreator;
  }
}
