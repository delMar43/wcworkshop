package wcworkshop.core.binary;

public class Wc1BriefingFile {

  private int filesize;
  private int[] offsets;
  //  private Wc1BriefingFuneralData funeralData;
  private Wc1BriefingFuneralData funeralData;
  private Wc1BriefingHalcyonData halcyon;
  private Wc1BriefingMedalCeremonyData medalCeremony;
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

  public Wc1BriefingFuneralData getFuneralData() {
    return funeralData;
  }

  public void setFuneralData(Wc1BriefingFuneralData funeralData) {
    this.funeralData = funeralData;
  }

  public Wc1BriefingHalcyonData getHalcyon() {
    return halcyon;
  }

  public void setHalcyon(Wc1BriefingHalcyonData halcyon) {
    this.halcyon = halcyon;
  }

  public Wc1BriefingMedalCeremonyData getMedalCeremony() {
    return medalCeremony;
  }

  public void setMedalCeremony(Wc1BriefingMedalCeremonyData medalCeremony) {
    this.medalCeremony = medalCeremony;
  }

}
