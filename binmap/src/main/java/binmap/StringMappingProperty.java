package binmap;

public class StringMappingProperty extends MappingProperty {

  private int length;

  public StringMappingProperty(String property, int offset, int length, int times, boolean isBlockOffsetCreator) {
    super(property, offset, times, isBlockOffsetCreator);
    this.length = length;
  }

  public int getLength() {
    return length;
  }

}
