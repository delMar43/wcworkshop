package wcworkshop.core.data;

public class Wc1MissionSlot {
  public static final Wc1MissionSlot EMPTY = new Wc1MissionSlot();

  private Wc1Cutscene shotglassCutscene;
  private Wc1Cutscene leftCutscene;
  private Wc1Cutscene rightCutscene;
  private Wc1Cutscene briefingCutscene;
  private Wc1Cutscene debriefingCutscene;

  private Wc1ConversationPartners conversationPartners;
  private short medal;
  private short medalKillPoints;
  private byte[] objectiveVictoryPoints;

  public Wc1Cutscene getShotglassCutscene() {
    return shotglassCutscene;
  }

  public void setShotglassCutscene(Wc1Cutscene shotglassCutscene) {
    this.shotglassCutscene = shotglassCutscene;
  }

  public Wc1Cutscene getLeftCutscene() {
    return leftCutscene;
  }

  public void setLeftCutscene(Wc1Cutscene leftCutscene) {
    this.leftCutscene = leftCutscene;
  }

  public Wc1Cutscene getRightCutscene() {
    return rightCutscene;
  }

  public void setRightCutscene(Wc1Cutscene rightCutscene) {
    this.rightCutscene = rightCutscene;
  }

  public Wc1Cutscene getBriefingCutscene() {
    return briefingCutscene;
  }

  public void setBriefingCutscene(Wc1Cutscene briefingCutscene) {
    this.briefingCutscene = briefingCutscene;
  }

  public Wc1Cutscene getDebriefingCutscene() {
    return debriefingCutscene;
  }

  public void setDebriefingCutscene(Wc1Cutscene debriefingCutscene) {
    this.debriefingCutscene = debriefingCutscene;
  }

  public Wc1ConversationPartners getConversationPartners() {
    return conversationPartners;
  }

  public void setConversationPartners(Wc1ConversationPartners conversationPartners) {
    this.conversationPartners = conversationPartners;
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
