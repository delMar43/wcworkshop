package wcworkshop.core.data;

import java.util.List;

public class Wc1SeriesSlot {

  private short wingman;
  private byte nrOfMissions;
  private short victoryPoints;
  private byte missionTreeLevel;
  private byte victoryDestination;
  private byte victoryShip;
  private byte lossDestination;
  private byte lossShip;

  private List<Wc1MissionSlot> missionSlots;

  public Wc1MissionSlot getMissionSlot(int missionIndex) {
    if (missionSlots == null || missionIndex > 3) {
      return null;
    }
    return missionSlots.get(missionIndex);
  }

  @Override
  public String toString() {
    return "Wingman: " + Wc1CampPilot.getByValue(wingman) + ", nrOfMissions: " + nrOfMissions + ", victoryPoints: " + victoryPoints
        + ", missionTreeLevel: " + missionTreeLevel + ", victoryDestination: " + victoryDestination + ", victoryShip: "
        + Wc1CampShip.getByValue(victoryShip) + ", lossDestination: " + lossDestination + ", lossShip: " + Wc1CampShip.getByValue(lossShip);
  }

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

  public List<Wc1MissionSlot> getMissionSlots() {
    return missionSlots;
  }

  public void setMissionSlots(List<Wc1MissionSlot> missionSlots) {
    this.missionSlots = missionSlots;
  }

}
