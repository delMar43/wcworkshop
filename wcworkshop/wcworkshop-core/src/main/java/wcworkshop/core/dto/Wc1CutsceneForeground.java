package wcworkshop.core.dto;

public enum Wc1CutsceneForeground {
  //@formatter:off
  BRIEFING_CROWD_MOVING((byte)0, "Briefing crowd moving"),
  BRIEFING_CROWD_STILL((byte)1, "Briefing crowd still"),
  HALCYON_AT_PODIUM((byte)2, "Halcyon at podium"),
  HALCYON_POINTING_TO_THE_NAV_MAP((byte)3, "Halcyon pointing to the nav map"),
  NAV_MAP_CLOSEUP((byte)4, "Nav map closeup"),
  BRIEFING_CROWD_STANDING_UP((byte)5, "Briefing crowd standing up"),
  EMPTY_ROOM_1((byte)6, "Empty room 1"),
  MIDRANGE_NAV_MAP((byte)7, "Mid-range nav map"),
  EMPTY_ROOM_2((byte)8, "Empty room"),
  HALF_WALL((byte)9, "Half wall"),
  CAMERA_PANS_RIGHT_ACROSS_BACK_OF_PILOTS((byte)10, "Camera pans right across backs of pilots"),
  CAMERA_HOLDS_ON_BACKS_OF_PILOTS((byte)11, "Camera holds on backs of pilots"),
  STARFIELD_DEBRIS_1((byte)12, "starfield debris shot 1"),
  STARFIELD_DEBRIS_2((byte)13, "starfield debris shot 2"),
  STARFIELD_DEBRIS_3((byte)14, "starfield debris shot 3"),
  CAMERA_HOLDS((byte)15, "Camera holds?"),
  HALCYON((byte)20, "Halcyon"),
  SPIRIT((byte)21, "Spirit"),
  HUNTER((byte)22, "Hunter"),
  BOSSMAN_JAZZ((byte)23, "Bossman/Jazz"),
  ICEMAN((byte)24, "Iceman"),
  ANGEL((byte)25, "Angel"),
  PALADIN_DOOMSDAY((byte)26, "Paladin/Doomsday"),
  MANIAC((byte)27, "Maniac"),
  KNIGHT((byte)28, "Knight"),
  PLAYER((byte)29, "Player"),
  SHOTGLASS((byte)30, "Shotglass"),
  END_CONVERSATION((byte)0xFE, "End Conversation");
  //@formatter:on

  private byte value;
  private String label;

  private Wc1CutsceneForeground(byte value, String label) {
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
