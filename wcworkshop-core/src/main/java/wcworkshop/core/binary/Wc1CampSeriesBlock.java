package wcworkshop.core.binary;

public class Wc1CampSeriesBlock {

  private short wingman;
  private byte nrOfMissions;
  private short victoryPoints;
  private byte missionTreeLevel;
  private byte victoryDestination;
  private byte victoryShip;
  private byte lossDestination;
  private byte lossShip;
  private Wc1CampMissionData[] missionData;

  public short getWingman() {
    return wingman;
  }

  public void setWingman(short wingman) {
    this.wingman = wingman;
  }

  public byte getNrOfMissions() {
    return nrOfMissions;
  }

  public void setNrOfMissions(byte nrOfMissions) {
    this.nrOfMissions = nrOfMissions;
  }

  public short getVictoryPoints() {
    return victoryPoints;
  }

  public void setVictoryPoints(short victoryPoints) {
    this.victoryPoints = victoryPoints;
  }

  public byte getMissionTreeLevel() {
    return missionTreeLevel;
  }

  public void setMissionTreeLevel(byte missionTreeLevel) {
    this.missionTreeLevel = missionTreeLevel;
  }

  public byte getVictoryDestination() {
    return victoryDestination;
  }

  public void setVictoryDestination(byte victoryDestination) {
    this.victoryDestination = victoryDestination;
  }

  public byte getVictoryShip() {
    return victoryShip;
  }

  public void setVictoryShip(byte victoryShip) {
    this.victoryShip = victoryShip;
  }

  public byte getLossDestination() {
    return lossDestination;
  }

  public void setLossDestination(byte lossDestination) {
    this.lossDestination = lossDestination;
  }

  public byte getLossShip() {
    return lossShip;
  }

  public void setLossShip(byte lossShip) {
    this.lossShip = lossShip;
  }

  public Wc1CampMissionData[] getMissionData() {
    return missionData;
  }

  public void setMissionData(Wc1CampMissionData[] missionData) {
    this.missionData = missionData;
  }

}
