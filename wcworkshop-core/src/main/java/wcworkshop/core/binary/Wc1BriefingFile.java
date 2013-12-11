package wcworkshop.core.binary;

public class Wc1BriefingFile {

  private int filesize;
  private int[] offsets;
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

}
