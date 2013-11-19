package wcworkshop.core.data;

public enum Wc1CampMedal {
  //@formatter:off
  BRONZESTAR((byte)0),
  SILVERSTAR((byte)1),
  GOLDSTAR((byte)2),
  GOLDENSUN((byte)3),
  VALOR((byte)4);
  //@formatter:on

  private byte value;

  private Wc1CampMedal(byte value) {
    this.value = value;
  }

  public static Wc1CampMedal getByValue(short value) {
    for (Wc1CampMedal medal : values()) {
      if (medal.value == value) {
        return medal;
      }
    }
    throw new RuntimeException("Unknown medal: " + value);
  }
}
