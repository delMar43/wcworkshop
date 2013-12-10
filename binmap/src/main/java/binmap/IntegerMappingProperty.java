package binmap;

public class IntegerMappingProperty extends MappingProperty {

  private boolean onlyThreeBytes;

  public IntegerMappingProperty(String property, int offset, int times) {
    this(property, offset, times, false);
  }

  public IntegerMappingProperty(String property, int offset, int times, boolean onlyThreeBytes) {
    super(property, offset, times);
    this.onlyThreeBytes = onlyThreeBytes;
  }

  public boolean isOnlyThreeBytes() {
    return onlyThreeBytes;
  }

}
