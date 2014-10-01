package wcworkshop.core.binary;

public class Wc1CampFile {

  private int filesize;
  private int[] offsets;
  private Wc1CampStellarBackground[] stellarBackgrounds;
  private Wc1CampSeriesBlock[] seriesBlocks;
  private Wc1CampBarData[] barData;

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

  public Wc1CampStellarBackground[] getStellarBackgrounds() {
    return stellarBackgrounds;
  }

  public void setStellarBackgrounds(Wc1CampStellarBackground[] stellarBackgrounds) {
    this.stellarBackgrounds = stellarBackgrounds;
  }

  public Wc1CampSeriesBlock[] getSeriesBlocks() {
    return seriesBlocks;
  }

  public void setSeriesBlocks(Wc1CampSeriesBlock[] seriesBlocks) {
    this.seriesBlocks = seriesBlocks;
  }

  public Wc1CampBarData[] getBarData() {
    return barData;
  }

  public void setBarData(Wc1CampBarData[] barData) {
    this.barData = barData;
  }

}
