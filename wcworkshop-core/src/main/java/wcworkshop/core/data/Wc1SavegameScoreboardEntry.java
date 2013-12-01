package wcworkshop.core.data;

public class Wc1SavegameScoreboardEntry {

  private String name;
  private String callsign;
  private short rank;
  private short sorties;
  private short kills;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCallsign() {
    return callsign;
  }

  public void setCallsign(String callsign) {
    this.callsign = callsign;
  }

  public short getRank() {
    return rank;
  }

  public void setRank(short rank) {
    this.rank = rank;
  }

  public short getSorties() {
    return sorties;
  }

  public void setSorties(short sorties) {
    this.sorties = sorties;
  }

  public short getKills() {
    return kills;
  }

  public void setKills(short kills) {
    this.kills = kills;
  }

}
