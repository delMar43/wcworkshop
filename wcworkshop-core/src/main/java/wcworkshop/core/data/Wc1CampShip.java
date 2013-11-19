package wcworkshop.core.data;

public enum Wc1CampShip {
  //@formatter:off
  UNKNOWN((byte)-1),
  HORNET((byte)0), 
  RAPIER((byte)1),
  SCIMITAR((byte)2),
  RAPTOR((byte)3);

  private byte value;

  private Wc1CampShip(byte value) {
    this.value = value;
  }

  public static Wc1CampShip getByValue(byte value) {
    for (Wc1CampShip ship : values()) {
      if (value == ship.value) {
        return ship;
      }
    }
    throw new RuntimeException("Unknown ship: " + value);
  }
}
