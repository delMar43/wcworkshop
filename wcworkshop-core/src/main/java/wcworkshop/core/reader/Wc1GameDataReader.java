package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1ConversationPartners;
import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1MissionShipData;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1ModuleData;
import wcworkshop.core.data.Wc1NavPoint;
import wcworkshop.core.data.Wc1NavPointInfo;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1GameDataReader {
  private static final Wc1GameDataReader instance = new Wc1GameDataReader();

  private Configuration config = Configuration.getInstance();
  private Wc1BriefingReader briefingReader = Wc1BriefingReader.getInstance();
  private Wc1CampaignReader campaignReader = Wc1CampaignReader.getInstance();
  private Wc1ModuleReader moduleReader = Wc1ModuleReader.getInstance();

  private Map<String, Wc1GameData> gameDataCache = new HashMap<>();

  public Wc1GameData readData(String extension) {
    Wc1GameData gameData = gameDataCache.get(extension);

    if (gameData == null) {
      gameData = new Wc1GameData();
      gameData.setCampaignName(extension);

      Wc1CampData campaignData = campaignReader.readCampaignFile(config.getResourcePath() + "CAMP." + extension);
      Wc1BriefingData briefingData = briefingReader.readBriefingFile(config.getResourcePath() + "BRIEFING." + extension);
      Wc1ModuleData moduleData = moduleReader.readModuleFile(config.getResourcePath() + "MODULE." + extension);

      List<Wc1SeriesSlot> seriesSlots = new ArrayList<>();
      for (Wc1SeriesSlot seriesSlot : campaignData.getSeriesSlots()) {
        if (seriesSlot.getNrOfMissions() > 0) {
          seriesSlots.add(seriesSlot);
        }
      }
      gameData.setSeriesSlots(seriesSlots);

      List<Wc1MissionSlot> allMissionSlots = briefingData.getMissionSlots();
      List<Wc1ConversationPartners> allConversationPartners = campaignData.getConversationPartners();
      List<short[]> unknownCampData = campaignData.getFirstBlock();

      int seriesIndex = 0;
      for (Wc1SeriesSlot seriesSlot : gameData.getSeriesSlots()) {
        if (seriesSlot.getNrOfMissions() == (byte) 0) {
          break;
        }
        seriesSlot.setSystemName(moduleData.getSystemNames().get(seriesIndex));

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
          missionSlot.setWingName(moduleData.getWingNames().get(index));

          List<Wc1NavPoint> navPoints = new ArrayList<>();
          List<Wc1NavPointInfo> navPointInfos = new ArrayList<>();
          for (int navCtr = 0; navCtr < 16; ++navCtr) {
            int navIndex = index * 16 + navCtr;
            Wc1NavPoint navPoint = moduleData.getNavPoints().get(navIndex);
            if (navPoint.getShipsToLoad() == null || navPoint.getShipsToLoad().size() == 0) {
              break;
            }
            navPoints.add(navPoint);
            navPointInfos.add(moduleData.getNavPointInfo().get(navIndex));
          }

          List<Wc1MissionShipData> shipData = new ArrayList<>();
          for (int shipCtr = 0; shipCtr < 32; ++shipCtr) {
            int shipIndex = index * 32 + shipCtr;
            Wc1MissionShipData curShipData = moduleData.getMissionShipData().get(shipIndex);
            if (curShipData.getType() == -1) {
              break;
            }
            shipData.add(curShipData);
          }

          missionSlot.setShipData(shipData);
          missionSlot.setNavPoints(navPoints);
          missionSlot.setNavPointInfos(navPointInfos);

          toAdd.add(missionSlot);
        }
        seriesSlot.setMissionSlots(toAdd);
        ++seriesIndex;
      }

      gameDataCache.put(extension, gameData);
    }

    return gameData;
  }

  public static Wc1GameDataReader getInstance() {
    return instance;
  }
}
