package wcworkshop.core.workdata;

public class Block {

  private int offset;
  private boolean compressed;

  public Block() {
  }

  public Block(int offset, boolean compressed) {
    this.offset = offset;
    this.compressed = compressed;
  }

  @Override
  public String toString() {
    return "offset=" + offset + "|compressed=" + compressed;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public boolean isCompressed() {
    return compressed;
  }

  public void setCompressed(boolean compressed) {
    this.compressed = compressed;
  }

}
