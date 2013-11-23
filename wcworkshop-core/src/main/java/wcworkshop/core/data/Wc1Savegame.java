package wcworkshop.core.data;

public class Wc1Savegame {
  public static final Wc1Savegame EMPTY = new Wc1Savegame();

  private String name;
  private byte campaign;
  private byte series;
  private byte mission;

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

}
