package wcworkshop.core.data;

import java.util.List;

public class Wc1CampData {
  public static final Wc1CampData EMPTY = new Wc1CampData();

  private List<short[]> firstBlock;
  private List<Wc1SeriesSlot> seriesSlots;
  private List<Wc1ConversationPartners> conversationPartners;

  public int getNrSeries() {
    int result;
    if (seriesSlots != null) {
      result = seriesSlots.size();
    } else {
      result = 0;
    }
    return result;
  }

  public List<Wc1SeriesSlot> getSeriesSlots() {
    return seriesSlots;
  }

  public void setSeriesSlots(List<Wc1SeriesSlot> seriesSlots) {
    this.seriesSlots = seriesSlots;
  }

  public List<short[]> getFirstBlock() {
    return firstBlock;
  }

  public void setFirstBlock(List<short[]> firstBlock) {
    this.firstBlock = firstBlock;
  }

  public void setConversationPartners(List<Wc1ConversationPartners> conversationPartners) {
    this.conversationPartners = conversationPartners;
  }

  public List<Wc1ConversationPartners> getConversationPartners() {
    return conversationPartners;
  }
}
