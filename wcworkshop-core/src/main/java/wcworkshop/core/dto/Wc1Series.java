package wcworkshop.core.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wc1Series implements Series {

  private String id;
  private String systemName;
  private int seriesNr;
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

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public int getSeriesNr() {
    return seriesNr;
  }

  public void setSeriesNr(int seriesNr) {
    this.seriesNr = seriesNr;
  }

  public short getWingman() {
    return wingman;
  }

  public void setWingman(short wingman) {
    this.wingman = wingman;
  }

  @JsonIgnore
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
