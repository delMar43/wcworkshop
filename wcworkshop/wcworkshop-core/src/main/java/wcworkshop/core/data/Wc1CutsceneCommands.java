package wcworkshop.core.data;

public interface Wc1CutsceneCommands {
  public static final Wc1CutsceneCommand ALWAYS = new Wc1CutsceneCommand((byte) 0);
  public static final Wc1CutsceneCommand DAMAGE_ABOVE = new Wc1CutsceneCommand((byte) 1, true);
  public static final Wc1CutsceneCommand DAMAGE_BELOW = new Wc1CutsceneCommand((byte) 2, true);
  public static final Wc1CutsceneCommand PILOT_DEAD = new Wc1CutsceneCommand((byte) 3);
  public static final Wc1CutsceneCommand PILOT_ALIVE = new Wc1CutsceneCommand((byte) 4);
  public static final Wc1CutsceneCommand PILOT_DIED_CURRENT_MISSION = new Wc1CutsceneCommand((byte) 5);
  public static final Wc1CutsceneCommand BLUEHAIR_HAS_KILLS = new Wc1CutsceneCommand((byte) 6);
  public static final Wc1CutsceneCommand BLUEHAIR_HAS_NO_KILLS = new Wc1CutsceneCommand((byte) 7);
  public static final Wc1CutsceneCommand PILOT_HAS_KILLS = new Wc1CutsceneCommand((byte) 8);
  public static final Wc1CutsceneCommand MEET_NOT_WITH_HALCYON = new Wc1CutsceneCommand((byte) 9);
  public static final Wc1CutsceneCommand OBJECTIVE_FAILED = new Wc1CutsceneCommand((byte) 10);
  public static final Wc1CutsceneCommand OBJECTIVE_SUCCEEDED = new Wc1CutsceneCommand((byte) 11);
  public static final Wc1CutsceneCommand MEDAL_PEWTER_PLANET = new Wc1CutsceneCommand((byte) 12);
  public static final Wc1CutsceneCommand MEDAL_STAR = new Wc1CutsceneCommand((byte) 13);
  public static final Wc1CutsceneCommand MEDAL_GOLDEN_SUN = new Wc1CutsceneCommand((byte) 14);
  public static final Wc1CutsceneCommand NO_PROMOTION = new Wc1CutsceneCommand((byte) 15);
  public static final Wc1CutsceneCommand NOT_EJECTED = new Wc1CutsceneCommand((byte) 16);
  public static final Wc1CutsceneCommand FIRST_EJECTION = new Wc1CutsceneCommand((byte) 17);
  public static final Wc1CutsceneCommand NOT_TRANSFERRING_SQUADRON = new Wc1CutsceneCommand((byte) 18);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_HORNET = new Wc1CutsceneCommand((byte) 19);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_SCIMITAR = new Wc1CutsceneCommand((byte) 20);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_RAPTOR = new Wc1CutsceneCommand((byte) 21);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_RAPIER = new Wc1CutsceneCommand((byte) 22);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_INFERIOR_SHIP = new Wc1CutsceneCommand((byte) 23);
  public static final Wc1CutsceneCommand TRANSFERRING_TO_SUPERIOR_SHIP = new Wc1CutsceneCommand((byte) 24);
  public static final Wc1CutsceneCommand EJECTION_SECOND = new Wc1CutsceneCommand((byte) 25);
  public static final Wc1CutsceneCommand OBJECTIVE_SIGHTED = new Wc1CutsceneCommand((byte) 26);
  public static final Wc1CutsceneCommand OBJECTIVE_NOT_SIGHTED = new Wc1CutsceneCommand((byte) 27);
  public static final Wc1CutsceneCommand PILOT_SURVIVED_CURRENT_MISSION = new Wc1CutsceneCommand((byte) 28);
  public static final Wc1CutsceneCommand PILOT_DIED_CURRENT_MISSION1 = new Wc1CutsceneCommand((byte) 29);
  public static final Wc1CutsceneCommand KILRATHI_ACE_DEAD = new Wc1CutsceneCommand((byte) 30);
  public static final Wc1CutsceneCommand KILRATHI_ACE_SURVIVED_PREVIOUS_MISSIONS = new Wc1CutsceneCommand((byte) 31);
  public static final Wc1CutsceneCommand KILRATHI_ACE_ALIVE = new Wc1CutsceneCommand((byte) 32);
  public static final Wc1CutsceneCommand KILRATHI_ACE_DIED_PREVIOUS_MISSIONS = new Wc1CutsceneCommand((byte) 33);
  public static final Wc1CutsceneCommand MISSION_WENT_WELL = new Wc1CutsceneCommand((byte) 34);
  public static final Wc1CutsceneCommand MISSION_WENT_BAD = new Wc1CutsceneCommand((byte) 35);
}
