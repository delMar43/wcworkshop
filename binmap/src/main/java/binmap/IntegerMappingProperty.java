package binmap;

public class IntegerMappingProperty extends MappingProperty {

  private boolean onlyThreeBytes;

  public IntegerMappingProperty(String property, int offset, int times, boolean isBlockOffsetCreator) {
    this(property, offset, times, false, isBlockOffsetCreator);
  }

  public IntegerMappingProperty(String property, int offset, int times, boolean onlyThreeBytes, boolean isBlockOffsetCreator) {
    super(property, offset, times, isBlockOffsetCreator);
    this.onlyThreeBytes = onlyThreeBytes;
  }

  public boolean isOnlyThreeBytes() {
    return onlyThreeBytes;
  }

}
