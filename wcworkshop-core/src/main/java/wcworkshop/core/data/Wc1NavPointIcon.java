package wcworkshop.core.data;

public enum Wc1NavPointIcon {
  //@formatter:off
  NONE((short) -1),
  NAV_POINT((short) 0),
  HOME_BASE((short) 1),
  FRIENDLY_SHIP_PURPLE((short) 2),
  FRIENDLY_SHIP_GREEN((short) 3),
  ENEMY_CRAFT((short) 4);
  //@formatter:on

  private short value;

  public static Wc1NavPointIcon getByValue(short value) {
    for (Wc1NavPointIcon icon : values()) {
      if (icon.value == value) {
        return icon;
      }
    }
    throw new RuntimeException("Unknown icon: " + value);
  }

  private Wc1NavPointIcon(short value) {
    this.value = value;
  }
}
