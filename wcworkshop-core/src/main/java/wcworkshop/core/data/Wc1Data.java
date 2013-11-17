package wcworkshop.core.data;

import java.util.List;

public interface Wc1Data {

  void setFilesize(int filesize);

  void setBlockOffsets(List<Integer> offsets);

  int getBlockOffset(int offsetIndex);
}
