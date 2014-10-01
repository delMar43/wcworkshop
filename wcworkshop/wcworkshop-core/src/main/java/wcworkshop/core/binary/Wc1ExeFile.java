package wcworkshop.core.binary;

public class Wc1ExeFile {

  private byte[] unknown1;
  private short startDay;
  private short startYear;
  private byte[] unknown2;
  private Wc1SavegameScoreboardEntry[] pilotEntries;
  private byte[] unknown3;
  private String introText;
  private byte[] unknown4;

  public byte[] getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(byte[] unknown1) {
    this.unknown1 = unknown1;
  }

  public short getStartDay() {
    return startDay;
  }

  public void setStartDay(short startDay) {
    this.startDay = startDay;
  }

  public short getStartYear() {
    return startYear;
  }

  public void setStartYear(short startYear) {
    this.startYear = startYear;
  }

  public byte[] getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(byte[] unknown2) {
    this.unknown2 = unknown2;
  }

  public Wc1SavegameScoreboardEntry[] getPilotEntries() {
    return pilotEntries;
  }

  public void setPilotEntries(Wc1SavegameScoreboardEntry[] pilotEntries) {
    this.pilotEntries = pilotEntries;
  }

  public byte[] getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(byte[] unknown3) {
    this.unknown3 = unknown3;
  }

  public String getIntroText() {
    return introText;
  }

  public void setIntroText(String introText) {
    this.introText = introText;
  }

  public byte[] getUnknown4() {
    return unknown4;
  }

  public void setUnknown4(byte[] unknown4) {
    this.unknown4 = unknown4;
  }
}
