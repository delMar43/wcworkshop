package wcworkshop.core.model;

public enum Wc1CutsceneTextColor {
  // TODO: replace hardcoded pilot strings with some pattern to handle them dynamically on the ui
  //@formatter:off
  LIGHT_BLUE((byte)0, "Light blue (Halcyon)"),
  LAVENDAR((byte)1, "Lavendar (Spirit)"),
  ROYAL_BLUE((byte)2, "Royal Blue (Hunter)"),
  RED((byte)3, "Red (Bossman / Jazz)"),
  CYAN((byte)4, "Cyan (Iceman)"),
  PEACH((byte)5, "Peach (Angel)"),
  BLUE((byte)6, "Blue (Paladin / Doomsday)"),
  ELECTRIC_GREEN((byte)7, "Electric Green (Maniac)"),
  BROWN((byte)8, "Brown (Knight)"),
  YELLOW((byte)9, "Yellow (Player)"),
  PEA_GREEN((byte)10, "Pea Green (Shotglass)"),
  MEDIUM_LIGHT_GRAY((byte)11,"Medium light gray"),
  MEDIUM_GRAY((byte)12, "Medium gray"),
  VERY_LIGHT_GRAY((byte)13, "Very light gray"),
  VERY_DARK_GRAY((byte)14, "Very dark gray");
  //@formatter:on

  private byte value;
  private String label;

  private Wc1CutsceneTextColor(byte value, String label) {
    this.value = value;
    this.label = label;
  }

  public byte getValue() {
    return value;
  }

  public String getLabel() {
    return label;
  }
}
