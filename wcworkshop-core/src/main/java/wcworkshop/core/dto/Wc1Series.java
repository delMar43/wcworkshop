package wcworkshop.core.dto;

import java.util.List;

public class Wc1Series implements Series {

  private String id;
  private String systemName;
  private short wingman;
  private short victoryPoints;
  private byte missionTreeLevel;
  private byte victoryDestination;
  private byte victoryShip;
  private byte lossDestination;
  private byte lossShip;

  private List<Wc1Mission> missions;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSystem() {
    return systemName;
  }

  public void setSystem(String system) {
    this.systemName = system;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public short getWingman() {
    return wingman;
  }

  public void setWingman(short wingman) {
    this.wingman = wingman;
  }

  public byte getNrOfMissions() {
    if (missions == null) {
      return (byte) 0;
    } else {
      return (byte) missions.size();
    }
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

  public List<Wc1Mission> getMissions() {
    return missions;
  }

  public void setMissions(List<Wc1Mission> missions) {
    this.missions = missions;
  }

}
