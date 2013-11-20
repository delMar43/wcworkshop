package wcworkshop.core.data;

import java.util.List;

public class Wc1ModuleData implements Wc1Data {

  public static final Wc1ModuleData EMPTY = new Wc1ModuleData();

  private byte[] buffer;
  private int filesize;
  private List<Integer> blockOffsets;

  @Override
  public int getBlockOffset(int blockIndex) {
    if (blockOffsets == null) {
      throw new RuntimeException("BlockOffsets not yet set!");
    }
    if (blockIndex > blockOffsets.size()) {
      throw new RuntimeException("Index of " + blockIndex + " is too large. Last index is " + (blockOffsets.size() - 1));
    }

    return blockOffsets.get(blockIndex);
  }

  public byte[] getBuffer() {
    return buffer;
  }

  public void setBuffer(byte[] buffer) {
    this.buffer = buffer;
  }

  public int getFilesize() {
    return filesize;
  }

  @Override
  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }

  public List<Integer> getBlockOffsets() {
    return blockOffsets;
  }

  @Override
  public void setBlockOffsets(List<Integer> blockOffsets) {
    this.blockOffsets = blockOffsets;
  }

}
