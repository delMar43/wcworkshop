package wcworkshop.core.binary;

public class Wc1CampMissionData {

  private short medal;
  private short requiredMedalPoints;
  private short[] victoryPointsPerObjective;

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
