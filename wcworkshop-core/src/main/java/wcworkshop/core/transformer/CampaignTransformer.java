package wcworkshop.core.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1BriefingMissionData;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1CampMissionData;
import wcworkshop.core.binary.Wc1CampSeriesBlock;
import wcworkshop.core.binary.Wc1ModuleAutopilotShips;
import wcworkshop.core.binary.Wc1ModuleFile;
import wcworkshop.core.binary.Wc1ModuleMissionShipData;
import wcworkshop.core.binary.Wc1ModuleNavPointData;
import wcworkshop.core.binary.Wc1ModuleNavPointMapData;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.dto.Wc1ShipAssignment;

public class CampaignTransformer {
  private static final CampaignTransformer instance = new CampaignTransformer();

  public Wc1Campaign binaryToCampaign(Wc1ModuleFile moduleFile, Wc1BriefingFile briefingFile, Wc1CampFile campFile) {
    Wc1Campaign result = new Wc1Campaign();

    fillCampaignWithCampData(result, campFile);
    fillCampaignWithBriefingData(result, briefingFile);
    fillCampaignWithModuleData(result, moduleFile);

    return result;
  }

  private void fillCampaignWithCampData(Wc1Campaign result, Wc1CampFile campFile) {
    List<Wc1Series> seriesList = new ArrayList<>();
    for (int seriesIdx = 0; seriesIdx < campFile.getSeriesBlocks().length; ++seriesIdx) {
      Wc1CampSeriesBlock seriesBlock = campFile.getSeriesBlocks()[seriesIdx];

      Wc1Series series = new Wc1Series();
      series.setId(UUID.randomUUID().toString());
      series.setSeriesNr(seriesIdx + 1);
      series.setWingman(seriesBlock.getWingman());
      series.setLossDestination(seriesBlock.getLossDestination());
      series.setLossShip(seriesBlock.getLossShip());
      series.setMissionTreeLevel(seriesBlock.getMissionTreeLevel());
      series.setVictoryDestination(seriesBlock.getVictoryDestination());
      series.setVictoryShip(seriesBlock.getVictoryShip());
      series.setVictoryPoints(seriesBlock.getVictoryPoints());

      List<Wc1Mission> missionList = new ArrayList<>();
      for (int missionIdx = 0; missionIdx < seriesBlock.getNrOfMissions(); ++missionIdx) {
        Wc1CampMissionData missionData = seriesBlock.getMissionData()[missionIdx];

        Wc1Mission mission = new Wc1Mission();
        mission.setId(UUID.randomUUID().toString());
        mission.setMedal(missionData.getMedal());
        mission.setRequiredMedalPoints(missionData.getRequiredMedalPoints());
        mission.setVictoryPointsPerObjective(missionData.getVictoryPointsPerObjective());
        missionList.add(mission);
      }
      series.setMissions(missionList);

      seriesList.add(series);
    }

    result.setSeries(seriesList);
  }

  private void fillCampaignWithBriefingData(Wc1Campaign result, Wc1BriefingFile briefingFile) {
    Wc1BriefingMissionData[] missionData = briefingFile.getMissionData();
    for (Wc1BriefingMissionData data : missionData) {

    }
  }

  private void fillCampaignWithModuleData(Wc1Campaign result, Wc1ModuleFile moduleFile) {
    Wc1ModuleAutopilotShips[] autopilotShips = moduleFile.getBlock1().getAutopilotShips();
    Wc1ModuleNavPointData[] navPointData = moduleFile.getBlock2().getNavPointData();
    Wc1ModuleNavPointMapData[] navPointMapData = moduleFile.getBlock3().getNavPointMapData();
    Wc1ModuleMissionShipData[] missionShipData = moduleFile.getBlock4().getMissionShipData();

    for (int seriesIdx = 0; seriesIdx < 13; ++seriesIdx) {
      String systemName = moduleFile.getSystemNames()[seriesIdx].replaceAll("\0", "");
      Wc1Series series = result.getSeries().get(seriesIdx);
      series.setSystemName(systemName);

      for (int missionIdx = 0; missionIdx < 4; ++missionIdx) {
        if (missionIdx >= series.getMissions().size()) {
          break;
        }

        int totalIdx = seriesIdx * 4 + missionIdx;
        String wingName = moduleFile.getWingNames()[totalIdx];

        Wc1Mission mission = series.getMissions().get(missionIdx);
        mission.setWingName(wingName);
        mission.setAutopilotShips(autopilotShips[totalIdx].getData());

        List<Wc1NavPoint> navPoints = new ArrayList<>(16);
        for (int navIdx = 0; navIdx < 16; ++navIdx) {
          int curNavIdx = 16 * totalIdx + navIdx;
          Wc1ModuleNavPointData curNavPointData = navPointData[curNavIdx];
          if (curNavPointData.getType() == (short) 0) {
            continue;
          }

          Wc1NavPoint navPoint = new Wc1NavPoint();
          navPoint.setId(curNavPointData.getId());
          navPoint.setType(curNavPointData.getType());

          Wc1ModuleNavPointMapData curNavPointMapData = navPointMapData[curNavIdx];
          navPoint.setShipOrPoint(curNavPointMapData.getShipOrPoint());
          navPoint.setIconValue(curNavPointMapData.getIconValue());
          navPoint.setText(curNavPointMapData.getText());

          navPoints.add(navPoint);
        }
        mission.setNavPoints(navPoints);

        List<Wc1ShipAssignment> shipAssignments = new ArrayList<>(32);
        for (int shipIdx = 0; shipIdx < 32; ++shipIdx) {
          int curShipIdx = 32 * totalIdx + shipIdx;
          Wc1ModuleMissionShipData curMissionShipData = missionShipData[curShipIdx];
        }
        mission.setShipAssignments(shipAssignments);
      }

    }
  }

  private CampaignTransformer() {
  }

  public static CampaignTransformer getInstance() {
    return instance;
  }
}
