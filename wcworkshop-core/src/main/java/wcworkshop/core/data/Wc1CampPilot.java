package wcworkshop.core.data;

public enum Wc1CampPilot {
  //@formatter:off
  EMPTY((byte) -1),
  SPIRIT((byte) 0),
  HUNTER((byte) 1),
  BOSSMAN((byte) 2),
  ICEMAN((byte) 3),
  ANGEL((byte) 4),
  PALADIN((byte) 5),
  MANIAC((byte) 6),
  KNIGHT((byte) 7),
  UNKNOWN_11((byte)11),
  UNKNOWN_12((byte)12);
  //@formatter:on

  private byte value;

  private Wc1CampPilot(byte value) {
    this.value = value;
  }

  public static Wc1CampPilot getByValue(short value) {
    return getByValue((byte) value);
  }

  public static Wc1CampPilot getByValue(byte value) {
    for (Wc1CampPilot pilot : values()) {
      if (pilot.value == value) {
        return pilot;
      }
    }
    throw new RuntimeException("Pilot not found: " + value);
  }
}
