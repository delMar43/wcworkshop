package wcworkshop.core.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wc1CampData implements Wc1Data {
  public static final Wc1CampData EMPTY = new Wc1CampData();

  private int filesize;
  private List<Integer> blockOffsets = new ArrayList<>();
  private Collection<Wc1MissionSlot> missionSlots;
  private Collection<Wc1SeriesSlot> seriesSlots;

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

  public Collection<Wc1MissionSlot> getMissionSlots() {
    return missionSlots;
  }

  public void setMissionSlots(Collection<Wc1MissionSlot> missionSlots) {
    this.missionSlots = missionSlots;
  }

  public Collection<Wc1SeriesSlot> getSeriesSlots() {
    return seriesSlots;
  }

  public void setSeriesSlots(Collection<Wc1SeriesSlot> seriesSlots) {
    this.seriesSlots = seriesSlots;
  }

  public static Wc1CampData getEmpty() {
    return EMPTY;
  }

}
