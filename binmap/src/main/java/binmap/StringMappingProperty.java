package binmap;

public class StringMappingProperty extends MappingProperty {

  private int length;

  public StringMappingProperty(String property, int offset, int length) {
    super(property, offset);
    this.length = length;
  }

  public int getLength() {
    return length;
  }

}
