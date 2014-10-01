package wcworkshop.core.dto;

public enum Wc1CutsceneBackground {
  //@formatter:off
  BRIEFING_ROOM_WALL((byte)0, "Briefing room wall"),
  BRIEFING_ROOM_CROWD((byte)1, "Briefing room crowd"),
  SHOTGLASS_WINDOW((byte)2, "Shotglass window"),
  LEFT_BAR_SEAT((byte)3, "Left bar seat"),
  RIGHT_BAR_SEAT((byte)4, "Right bar seat"),
  HALCYON_DEBRIEFING((byte)10, "Halcyon during debriefing"),
  PILOT_DEBRIEFING((byte)11, "Pilots during debriefing");
  //@formatter:on

  private byte value;
  private String label;

  private Wc1CutsceneBackground(byte value, String label) {
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
