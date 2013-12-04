package test;

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
  private byte ace1;
  private byte ace2;
  private byte ace3;
  private byte ace4;
  private short day;
  private short year;
  private short unknown2;
  private short unknown3;
  private byte promotion;
  private short victory;
  private short campaign;

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

  public byte getPromotion() {
    return promotion;
  }

  public void setPromotion(byte promotion) {
    this.promotion = promotion;
  }

  public short getVictory() {
    return victory;
  }

  public void setVictory(short victory) {
    this.victory = victory;
  }

}
