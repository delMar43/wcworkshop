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
  KNIGHT((byte) 7);
  //@formatter:on

  private byte value;

  private Wc1CampPilot(byte value) {
    this.value = value;
  }

  public static Wc1CampPilot getByValue(byte value) {
    for (Wc1CampPilot pilot : values()) {
      if (pilot.value == value) {
        return pilot;
      }
    }
    throw new RuntimeException("Pilot not found: " + value);
  }

  /*
  -1: Empty
  0 : Spirit
  1 : Hunter
  2 : Bossman/Jazz
  3 : Iceman
  4 : Angel
  5 : Paladin/Doomsday
  6 : Maniac
  7 : Knight
   */
}
