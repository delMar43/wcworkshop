package wcworkshop.core.binary;

public class UnknownDynamicBlock {

  private int filesize;
  private int[] offsets;
  private byte[] unknownBlock;

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

  public byte[] getUnknownBlock() {
    return unknownBlock;
  }

  public void setUnknownBlock(byte[] unknownBlock) {
    this.unknownBlock = unknownBlock;
  }
}
