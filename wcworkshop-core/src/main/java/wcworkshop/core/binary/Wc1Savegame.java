package wcworkshop.core.binary;

public class Wc1Savegame {
  public static final Wc1Savegame EMPTY = new Wc1Savegame();

  private String name;
  private byte unknown1;
  private byte occupied;
  private byte series;
  private byte mission;
  private Wc1SavegameScoreboardEntry[] scoreboardEntries;
  private short[] pilotStatus;
  private byte bronzeStars;
  private byte silverStars;
  private byte goldStars;
  private byte goldenSun;
  private byte pewterPlanet;
  private byte[] unknownBlock1;
  private byte ace1;
  private byte ace2;
  private byte ace3;
  private byte ace4;
  private short day;
  private short year;
  private short unknown2;
  private short unknown3;
  private int promotion;
  private short victory;
  private short campaign;
  private byte[] unknownBlock2;
  private byte seriesCount;
  private byte[] unknownBlock3;
  private byte[] unknownBlock4;
  private short unknown4;
  private short unknown5;

  public boolean isEnabled() {
    return occupied == (byte) 1;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(byte unknown1) {
    this.unknown1 = unknown1;
  }

  public short getCampaign() {
    return campaign;
  }

  public void setCampaign(short campaign) {
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

  public byte getBronzeStars() {
    return bronzeStars;
  }

  public void setBronzeStars(byte bronzeStars) {
    this.bronzeStars = bronzeStars;
  }

  public byte getSilverStars() {
    return silverStars;
  }

  public void setSilverStars(byte silverStars) {
    this.silverStars = silverStars;
  }

  public byte getGoldStars() {
    return goldStars;
  }

  public void setGoldStars(byte goldStars) {
    this.goldStars = goldStars;
  }

  public byte getGoldenSun() {
    return goldenSun;
  }

  public void setGoldenSun(byte goldenSun) {
    this.goldenSun = goldenSun;
  }

  public byte getPewterPlanet() {
    return pewterPlanet;
  }

  public void setPewterPlanet(byte pewterPlanet) {
    this.pewterPlanet = pewterPlanet;
  }

  public byte getAce1() {
    return ace1;
  }

  public void setAce1(byte ace1) {
    this.ace1 = ace1;
  }

  public byte getAce2() {
    return ace2;
  }

  public void setAce2(byte ace2) {
    this.ace2 = ace2;
  }

  public byte getAce3() {
    return ace3;
  }

  public void setAce3(byte ace3) {
    this.ace3 = ace3;
  }

  public byte getAce4() {
    return ace4;
  }

  public void setAce4(byte ace4) {
    this.ace4 = ace4;
  }

  public short getDay() {
    return day;
  }

  public void setDay(short day) {
    this.day = day;
  }

  public short getYear() {
    return year;
  }

  public void setYear(short year) {
    this.year = year;
  }

  public short getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(short unknown2) {
    this.unknown2 = unknown2;
  }

  public short getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(short unknown3) {
    this.unknown3 = unknown3;
  }

  public int getPromotion() {
    return promotion;
  }

  public void setPromotion(int promotion) {
    this.promotion = promotion;
  }

  public short getVictory() {
    return victory;
  }

  public void setVictory(short victory) {
    this.victory = victory;
  }

  public byte getSeriesCount() {
    return seriesCount;
  }

  public void setSeriesCount(byte seriesCount) {
    this.seriesCount = seriesCount;
  }

  public byte[] getUnknownBlock1() {
    return unknownBlock1;
  }

  public void setUnknownBlock1(byte[] unknownBlock1) {
    this.unknownBlock1 = unknownBlock1;
  }

  public byte[] getUnknownBlock2() {
    return unknownBlock2;
  }

  public void setUnknownBlock2(byte[] unknownBlock2) {
    this.unknownBlock2 = unknownBlock2;
  }

  public byte[] getUnknownBlock3() {
    return unknownBlock3;
  }

  public void setUnknownBlock3(byte[] unknownBlock3) {
    this.unknownBlock3 = unknownBlock3;
  }

  public short getUnknown4() {
    return unknown4;
  }

  public void setUnknown4(short unknown4) {
    this.unknown4 = unknown4;
  }

  public short getUnknown5() {
    return unknown5;
  }

  public void setUnknown5(short unknown5) {
    this.unknown5 = unknown5;
  }

  public byte[] getUnknownBlock4() {
    return unknownBlock4;
  }

  public void setUnknownBlock4(byte[] unknownBlock4) {
    this.unknownBlock4 = unknownBlock4;
  }
}
