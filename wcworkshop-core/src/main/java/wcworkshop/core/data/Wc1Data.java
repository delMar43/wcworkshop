package wcworkshop.core.data;

import java.util.Collections;
import java.util.List;

public class Wc1Data {

  private List<Wc1SeriesSlot> seriesSlots;

  public List<Wc1SeriesSlot> getSeriesSlots() {
    List<Wc1SeriesSlot> result;
    if (seriesSlots != null) {
      result = Collections.unmodifiableList(seriesSlots);
    } else {
      result = Collections.EMPTY_LIST;
    }
    return result;
  }

  public void setSeriesSlots(List<Wc1SeriesSlot> seriesSlots) {
    this.seriesSlots = seriesSlots;
  }
}
