package binmap;

public class SubMappingProperty extends MappingProperty {

  private Mapping subMapping;

  public SubMappingProperty(String property, int offset, Mapping subMapping, int times) {
    super(property, offset, times);
    this.subMapping = subMapping;
  }

  public Mapping getSubMapping() {
    return subMapping;
  }

}
