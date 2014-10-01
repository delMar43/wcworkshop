package wcworkshop.core.binary;

public class Wc1CampMissionData {

  public static final Wc1CampMissionData EMPTY = new Wc1CampMissionData((short) 0, (short) 0, new short[8]);

  private short medal;
  private short requiredMedalPoints;
  private short[] victoryPointsPerObjective;

  public Wc1CampMissionData() {
  }

  public Wc1CampMissionData(short medal, short requiredMedalPoints, short[] victoryPointsPerObjective) {
    this.medal = medal;
    this.requiredMedalPoints = requiredMedalPoints;
    this.victoryPointsPerObjective = victoryPointsPerObjective;
  }

  public short getMedal() {
    return medal;
  }

  public void setMedal(short medal) {
    this.medal = medal;
  }

  public short getRequiredMedalPoints() {
    return requiredMedalPoints;
  }

  public void setRequiredMedalPoints(short requiredMedalPoints) {
    this.requiredMedalPoints = requiredMedalPoints;
  }

  public short[] getVictoryPointsPerObjective() {
    return victoryPointsPerObjective;
  }

  public void setVictoryPointsPerObjective(short[] victoryPointsPerObjective) {
    this.victoryPointsPerObjective = victoryPointsPerObjective;
  }

}
