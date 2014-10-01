package wcworkshop.core.binary;

public class Wc1VgaFile {

  private int filesize;
  private int[] offsets;
  private Wc1VgaBlock[] blocks;

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

  public Wc1VgaBlock[] getBlocks() {
    return blocks;
  }

  public void setBlocks(Wc1VgaBlock[] blocks) {
    this.blocks = blocks;
  }

}
