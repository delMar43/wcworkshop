package wcworkshop.core.dto;

import java.util.List;

public class Wc1Series implements Series {

  private String id;
  private String system;
  private short wingman;
  private byte nrOfMissions;
  private short victoryPoints;
  private byte missionTreeLevel;
  private byte victoryDestination;
  private byte victoryShip;
  private byte lossDestination;
  private byte lossShip;

  private List<Wc1Mission> missions;

  public Wc1Series(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getSystem() {
    return system;
  }
}
