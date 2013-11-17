package wcworkshop.core.data;

public class Wc1MissionSlot {

  private Wc1CutsceneScreen shotglassCutscene;
  private Wc1CutsceneScreen leftCutscene;
  private Wc1CutsceneScreen rightCutscene;
  private Wc1CutsceneScreen briefingCutscene;
  private Wc1CutsceneScreen debriefingCutscene;

  private byte leftSeat;
  private byte rightSeat;
  private short medal;
  private short medalKillPoints;
  private byte[] objectiveVictoryPoints;

  public Wc1CutsceneScreen getShotglassCutscene() {
    return shotglassCutscene;
  }

  public void setShotglassCutscene(Wc1CutsceneScreen shotglassCutscene) {
    this.shotglassCutscene = shotglassCutscene;
  }

  public Wc1CutsceneScreen getLeftCutscene() {
    return leftCutscene;
  }

  public void setLeftCutscene(Wc1CutsceneScreen leftCutscene) {
    this.leftCutscene = leftCutscene;
  }

  public Wc1CutsceneScreen getRightCutscene() {
    return rightCutscene;
  }

  public void setRightCutscene(Wc1CutsceneScreen rightCutscene) {
    this.rightCutscene = rightCutscene;
  }

  public Wc1CutsceneScreen getBriefingCutscene() {
    return briefingCutscene;
  }

  public void setBriefingCutscene(Wc1CutsceneScreen briefingCutscene) {
    this.briefingCutscene = briefingCutscene;
  }

  public Wc1CutsceneScreen getDebriefingCutscene() {
    return debriefingCutscene;
  }

  public void setDebriefingCutscene(Wc1CutsceneScreen debriefingCutscene) {
    this.debriefingCutscene = debriefingCutscene;
  }

  public byte getLeftSeat() {
    return leftSeat;
  }

  public void setLeftSeat(byte leftSeat) {
    this.leftSeat = leftSeat;
  }

  public byte getRightSeat() {
    return rightSeat;
  }

  public void setRightSeat(byte rightSeat) {
    this.rightSeat = rightSeat;
  }

  public short getMedal() {
    return medal;
  }

  public void setMedal(short medal) {
    this.medal = medal;
  }

  public short getMedalKillPoints() {
    return medalKillPoints;
  }

  public void setMedalKillPoints(short medalKillPoints) {
    this.medalKillPoints = medalKillPoints;
  }

  public byte[] getObjectiveVictoryPoints() {
    return objectiveVictoryPoints;
  }

  public void setObjectiveVictoryPoints(byte[] objectiveVictoryPoints) {
    this.objectiveVictoryPoints = objectiveVictoryPoints;
  }
}
