package wcworkshop.core.data;

public enum Wc1CutsceneCommand {
  //@formatter:off
  ALWAYS((byte) 0),
  DAMAGE_ABOVE((byte)1),
  DAMAGE_BELOW((byte)2),
  PILOT_DEAD((byte)3),
  PILOT_ALIVE((byte)4),
  PILOT_DIED_CURRENT_MISSION((byte)5),
  BLUEHAIR_HAS_KILLS((byte)6),
  BLUEHAIR_HAS_NO_KILLS((byte)7),
  PILOT_HAS_KILLS((byte)8),
  MEET_NOT_WITH_HALCYON((byte)9),
  OBJECTIVE_FAILED((byte)10),
  OBJECTIVE_SUCCEEDED((byte)11),
  MEDAL_PEWTER_PLANET((byte)12),
  MEDAL_STAR((byte)13),
  MEDAL_GOLDEN_SUN((byte)14),
  NO_PROMOTION((byte)15),
  NOT_EJECTED((byte)16),
  FIRST_EJECTION((byte)17),
  NOT_TRANSFERRING_SQUADRON((byte)18),
  TRANSFERRING_TO_HORNET((byte)19),
  TRANSFERRING_TO_SCIMITAR((byte) 20),
  TRANSFERRING_TO_RAPTOR((byte)21),
  TRANSFERRING_TO_RAPIER((byte)22),
  TRANSFERRING_TO_INFERIOR_SHIP((byte)23),
  TRANSFERRING_TO_SUPERIOR_SHIP((byte)24),
  EJECTION_SECOND((byte)25),
  OBJECTIVE_SIGHTED((byte)26),
  OBJECTIVE_NOT_SIGHTED((byte)27),
  PILOT_SURVIVED_CURRENT_MISSION((byte)28),
  PILOT_DIED_CURRENT_MISSION1((byte)29),
  KILRATHI_ACE_DEAD((byte)30),
  KILRATHI_ACE_SURVIVED_PREVIOUS_MISSIONS((byte)31),
  KILRATHI_ACE_ALIVE((byte)32),
  KILRATHI_ACE_DIED_PREVIOUS_MISSIONS((byte)33),
  MISSION_WENT_WELL((byte)34),
  MISSION_WENT_BAD((byte)35);
  //@formatter:on

  private byte value;

  public static Wc1CutsceneCommand getByValue(byte value) {
    for (Wc1CutsceneCommand cmd : values()) {
      if (value == cmd.value) {
        return cmd;
      }
    }
    //    throw new RuntimeException("Unknown cutscene command: " + value);
    return null;
  }

  private Wc1CutsceneCommand(byte value) {
    this.value = value;
  }
}
