package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1ConversationPartners;
import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1GameDataReader {
  private static final Wc1GameDataReader instance = new Wc1GameDataReader();

  private Configuration config = Configuration.getInstance();
  private Wc1BriefingReader briefingReader = Wc1BriefingReader.getInstance();
  private Wc1CampaignReader campaignReader = Wc1CampaignReader.getInstance();

  private Wc1GameData gameData;

  public Wc1GameData readData() {
    if (gameData == null) {
      gameData = new Wc1GameData();
      gameData.setCampaignName("Vega Campaign 2654");

      Wc1CampData campaignData = campaignReader.readCampaignFile(config.getResourcePath() + "CAMP.000");
      Wc1BriefingData briefingData = briefingReader.readBriefingFile(config.getResourcePath() + "BRIEFING.000");

      gameData.setSeriesSlots(campaignData.getSeriesSlots());

      List<Wc1MissionSlot> allMissionSlots = briefingData.getMissionSlots();
      List<Wc1ConversationPartners> allConversationPartners = campaignData.getConversationPartners();
      List<short[]> unknownCampData = campaignData.getFirstBlock();

      int seriesIndex = 0;
      for (Wc1SeriesSlot seriesSlot : gameData.getSeriesSlots()) {
        List<Wc1MissionSlot> toAdd = new ArrayList<>();
        for (int missionIndex = 0; missionIndex < 4; ++missionIndex) {
          int index = missionIndex + (seriesIndex * 4);
          Wc1MissionSlot missionSlot = allMissionSlots.get(index);
          if (missionSlot.isEmpty()) {
            break;
          }
          Wc1MissionSlot missionFromCampaign = seriesSlot.getMissionSlot(missionIndex);
          missionSlot.setConversationPartners(allConversationPartners.get(index));
          missionSlot.setUnknown(unknownCampData.get(index));
          missionSlot.setMedal(missionFromCampaign.getMedal());
          missionSlot.setMedalKillPoints(missionFromCampaign.getMedalKillPoints());
          toAdd.add(missionSlot);
        }
        seriesSlot.setMissionSlots(toAdd);
        ++seriesIndex;
      }
    }

    return gameData;
  }

  public static Wc1GameDataReader getInstance() {
    return instance;
  }
}
