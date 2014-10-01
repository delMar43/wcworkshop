package wcworkshop.core.data;

import java.util.Collections;
import java.util.List;

public class Wc1GameData {

  private String campaignName;
  private List<Wc1SeriesSlot> seriesSlots;

  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

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
