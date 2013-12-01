package binmap;

public class ArrayMappingProperty extends MappingProperty {

  private int times;

  public ArrayMappingProperty(String property, int offset, int times) {
    super(property, offset);
    this.times = times;
  }

  public int getTimes() {
    return times;
  }
}
