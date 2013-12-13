package wcworkshop.core.binary;

public class Wc1BriefingFile {

  private int filesize;
  private int[] offsets;
  //  private Wc1BriefingFuneralData funeralData;
  private byte[] funeralData;
  private byte[] halcyon;
  private byte[] medalCeremony;
  private byte empty;
  private Wc1BriefingMissionData[] missionData;

  public int getFilesize() {
    return filesize;
  }

  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }

  public int[] getOffsets() {
    return offsets;
  }

  public void setOffsets(int[] offsets) {
    this.offsets = offsets;
  }

  public Wc1BriefingMissionData[] getMissionData() {
    return missionData;
  }

  public void setMissionData(Wc1BriefingMissionData[] missionData) {
    this.missionData = missionData;
  }

  public byte[] getFuneralData() {
    return funeralData;
  }

  public void setFuneralData(byte[] funeralData) {
    this.funeralData = funeralData;
  }

  public byte[] getHalcyon() {
    return halcyon;
  }

  public void setHalcyon(byte[] halcyon) {
    this.halcyon = halcyon;
  }

  public byte[] getMedalCeremony() {
    return medalCeremony;
  }

  public void setMedalCeremony(byte[] medalCeremony) {
    this.medalCeremony = medalCeremony;
  }
}
