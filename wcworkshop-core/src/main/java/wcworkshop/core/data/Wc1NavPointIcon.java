package wcworkshop.core.data;

public enum Wc1NavPointIcon {
  NAV_POINT((byte) 0), HOME_BASE((byte) 1), FRIENDLY_SHIP_PURPLE((byte) 2), FRIENDLY_SHIP_GREEN((byte) 3), ENEMY_CRAFT((byte) 4);

  private byte value;

  public static Wc1NavPointIcon getByValue(byte value) {
    for (Wc1NavPointIcon icon : values()) {
      if (icon.value == value) {
        return icon;
      }
    }
    throw new RuntimeException("Unknown icon: " + value);
  }

  private Wc1NavPointIcon(byte value) {
    this.value = value;
  }
}
