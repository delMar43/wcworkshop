package wcworkshop.core.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wc1CampData implements Wc1Data {
  public static final Wc1CampData EMPTY = new Wc1CampData();

  private int filesize;
  private List<Integer> blockOffsets = new ArrayList<>();
  private Collection<Wc1SeriesSlot> seriesSlots;
  private List<byte[]> firstBlock;

  public int getNrSeries() {
    int result;
    if (seriesSlots != null) {
      result = seriesSlots.size();
    } else {
      result = 0;
    }
    return result;
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

  public Collection<Wc1SeriesSlot> getSeriesSlots() {
    return seriesSlots;
  }

  public void setSeriesSlots(Collection<Wc1SeriesSlot> seriesSlots) {
    this.seriesSlots = seriesSlots;
  }

  public List<byte[]> getFirstBlock() {
    return firstBlock;
  }

  public void setFirstBlock(List<byte[]> firstBlock) {
    this.firstBlock = firstBlock;
  }
}
