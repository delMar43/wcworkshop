package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1ConversationPartners;
import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1GameDataReader {
  private static final Wc1GameDataReader instance = new Wc1GameDataReader();

  private Wc1BriefingReader briefingReader = Wc1BriefingReader.getInstance();
  private Wc1CampaignReader campaignReader = Wc1CampaignReader.getInstance();

  public Wc1GameData readData() {
    Wc1GameData result = new Wc1GameData();
    result.setCampaignName("Vega Campaign 2654");

    Wc1CampData campaignData = campaignReader.readCampaignFile("D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/CAMP.000");
    Wc1BriefingData briefingData = briefingReader.readBriefingFile("D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/BRIEFING.000");

    result.setSeriesSlots(campaignData.getSeriesSlots());

    List<Wc1MissionSlot> allMissionSlots = briefingData.getMissionSlots();
    List<Wc1ConversationPartners> allConversationPartners = campaignData.getConversationPartners();

    int seriesIndex = 0;
    for (Wc1SeriesSlot seriesSlot : result.getSeriesSlots()) {
      List<Wc1MissionSlot> toAdd = new ArrayList<>();
      for (int missionIndex = 0; missionIndex < 4; ++missionIndex) {
        int index = missionIndex + (seriesIndex * 4);
        Wc1MissionSlot missionSlot = allMissionSlots.get(index);
        missionSlot.setConversationPartners(allConversationPartners.get(missionIndex + (seriesIndex * 4)));
        toAdd.add(missionSlot);
      }
      seriesSlot.setMissionSlots(toAdd);
      ++seriesIndex;
    }

    return result;
  }

  public static Wc1GameDataReader getInstance() {
    return instance;
  }
}
