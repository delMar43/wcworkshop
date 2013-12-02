package binmap;

public class StringMappingProperty extends MappingProperty {

  private int length;

  public StringMappingProperty(String property, int offset, int length, int times) {
    super(property, offset, times);
    this.length = length;
  }

  public int getLength() {
    return length;
  }

}
