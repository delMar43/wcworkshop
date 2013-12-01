package binmap;

public class SubMappingProperty extends MappingProperty {

  private Mapping subMapping;
  private int times;

  public SubMappingProperty(String property, int offset, Mapping subMapping, int times) {
    super(property, offset);
    this.subMapping = subMapping;
    this.times = times;
  }

  public Mapping getSubMapping() {
    return subMapping;
  }

  public int getTimes() {
    return times;
  }

}
