package wcworkshop.core.data;


public class Wc1Savegame {
  public static final Wc1Savegame EMPTY = new Wc1Savegame();

  private String name;
  private byte campaign;
  private byte series;
  private byte mission;
  private byte occupied;
  private Wc1SavegameScoreboardEntry[] scoreboardEntries;
  private short[] pilotStatus;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte getCampaign() {
    return campaign;
  }

  public void setCampaign(byte campaign) {
    this.campaign = campaign;
  }

  public byte getSeries() {
    return series;
  }

  public void setSeries(byte series) {
    this.series = series;
  }

  public byte getMission() {
    return mission;
  }

  public void setMission(byte mission) {
    this.mission = mission;
  }

  public byte getOccupied() {
    return occupied;
  }

  public void setOccupied(byte occupied) {
    this.occupied = occupied;
  }

  public Wc1SavegameScoreboardEntry[] getScoreboardEntries() {
    return scoreboardEntries;
  }

  public void setScoreboardEntries(Wc1SavegameScoreboardEntry[] scoreboardEntries) {
    this.scoreboardEntries = scoreboardEntries;
  }

  public short[] getPilotStatus() {
    return pilotStatus;
  }

  public void setPilotStatus(short[] pilotStatus) {
    this.pilotStatus = pilotStatus;
  }
}
