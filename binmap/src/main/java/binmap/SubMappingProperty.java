package binmap;

public class SubMappingProperty extends MappingProperty {

  private Mapping subMapping;

  public SubMappingProperty(String property, int offset, Mapping subMapping, int times, String isBlockOffsetCreator) {
    super(property, offset, times, isBlockOffsetCreator);
    this.subMapping = subMapping;
  }

  public Mapping getSubMapping() {
    return subMapping;
  }

}
