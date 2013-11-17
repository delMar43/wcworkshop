package wcworkshop.core.data;

import java.util.List;

public class Wc1BriefingData implements Wc1Data {
  public static final Wc1BriefingData EMPTY = new Wc1BriefingData();

  private byte[] buffer;
  private int filesize;
  private List<Integer> blockOffsets;
  private Wc1FuneralData funeralData;
  private List<Wc1MissionSlot> missionSlots;

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

  public int getNrBlockOffsets() {
    return blockOffsets.size();
  }

  public List<Wc1MissionSlot> getMissionSlots() {
    return missionSlots;
  }

  public void setMissionSlots(List<Wc1MissionSlot> missionSlots) {
    this.missionSlots = missionSlots;
  }
}
