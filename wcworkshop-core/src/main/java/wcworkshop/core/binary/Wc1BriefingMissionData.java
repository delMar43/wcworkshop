package wcworkshop.core.binary;

public class Wc1BriefingMissionData {

  public static final Wc1BriefingMissionData EMPTY = new Wc1BriefingMissionData();

  private int[] offsets;
  private Wc1BriefingCutsceneSetting[] briefingCutsceneSettings;
  private Wc1BriefingCutsceneScript briefingCutsceneScript;
  private Wc1BriefingCutsceneSetting[] debriefingCutsceneSettings;
  private Wc1BriefingCutsceneScript debriefingCutsceneScript;
  private Wc1BriefingCutsceneSetting[] shotglassCutsceneSettings;
  private Wc1BriefingCutsceneScript shotglassCutsceneScript;
  private Wc1BriefingCutsceneSetting[] leftSeatCutsceneSettings;
  private Wc1BriefingCutsceneScript leftSeatCutsceneScript;
  private Wc1BriefingCutsceneSetting[] rightSeatCutsceneSettings;
  private Wc1BriefingCutsceneScript rightSeatCutsceneScript;

  public int[] getOffsets() {
    return offsets;
  }

  public void setOffsets(int[] offsets) {
    this.offsets = offsets;
  }

  public Wc1BriefingCutsceneSetting[] getBriefingCutsceneSettings() {
    return briefingCutsceneSettings;
  }

  public void setBriefingCutsceneSettings(Wc1BriefingCutsceneSetting[] briefingCutsceneSettings) {
    this.briefingCutsceneSettings = briefingCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getBriefingCutsceneScript() {
    return briefingCutsceneScript;
  }

  public void setBriefingCutsceneScript(Wc1BriefingCutsceneScript briefingCutsceneScript) {
    this.briefingCutsceneScript = briefingCutsceneScript;
  }

  public Wc1BriefingCutsceneSetting[] getDebriefingCutsceneSettings() {
    return debriefingCutsceneSettings;
  }

  public void setDebriefingCutsceneSettings(Wc1BriefingCutsceneSetting[] debriefingCutsceneSettings) {
    this.debriefingCutsceneSettings = debriefingCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getDebriefingCutsceneScript() {
    return debriefingCutsceneScript;
  }

  public void setDebriefingCutsceneScript(Wc1BriefingCutsceneScript debriefingCutsceneScript) {
    this.debriefingCutsceneScript = debriefingCutsceneScript;
  }

  public Wc1BriefingCutsceneSetting[] getShotglassCutsceneSettings() {
    return shotglassCutsceneSettings;
  }

  public void setShotglassCutsceneSettings(Wc1BriefingCutsceneSetting[] shotglassCutsceneSettings) {
    this.shotglassCutsceneSettings = shotglassCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getShotglassCutsceneScript() {
    return shotglassCutsceneScript;
  }

  public void setShotglassCutsceneScript(Wc1BriefingCutsceneScript shotglassCutsceneScript) {
    this.shotglassCutsceneScript = shotglassCutsceneScript;
  }

  public Wc1BriefingCutsceneSetting[] getLeftSeatCutsceneSettings() {
    return leftSeatCutsceneSettings;
  }

  public void setLeftSeatCutsceneSettings(Wc1BriefingCutsceneSetting[] leftSeatCutsceneSettings) {
    this.leftSeatCutsceneSettings = leftSeatCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getLeftSeatCutsceneScript() {
    return leftSeatCutsceneScript;
  }

  public void setLeftSeatCutsceneScript(Wc1BriefingCutsceneScript leftSeatCutsceneScript) {
    this.leftSeatCutsceneScript = leftSeatCutsceneScript;
  }

  public Wc1BriefingCutsceneSetting[] getRightSeatCutsceneSettings() {
    return rightSeatCutsceneSettings;
  }

  public void setRightSeatCutsceneSettings(Wc1BriefingCutsceneSetting[] rightSeatCutsceneSettings) {
    this.rightSeatCutsceneSettings = rightSeatCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getRightSeatCutsceneScript() {
    return rightSeatCutsceneScript;
  }

  public void setRightSeatCutsceneScript(Wc1BriefingCutsceneScript rightSeatCutsceneScript) {
    this.rightSeatCutsceneScript = rightSeatCutsceneScript;
  }

}
