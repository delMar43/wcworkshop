package wcworkshop.core.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1BriefingMissionData;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1CampMissionData;
import wcworkshop.core.binary.Wc1CampSeriesBlock;
import wcworkshop.core.binary.Wc1GameFiles;
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

  private Mapper dozer = new DozerBeanMapper();

  public Wc1Campaign binaryToCampaign(Wc1GameFiles gameFiles) {
    Wc1Campaign result = new Wc1Campaign();

    fillCampaignWithCampData(result, gameFiles.getCampFile());
    fillCampaignWithBriefingData(result, gameFiles.getBriefingFile());
    fillCampaignWithModuleData(result, gameFiles.getModuleFile());

    return result;
  }

  private void fillCampaignWithCampData(Wc1Campaign result, Wc1CampFile campFile) {
    List<Wc1Series> seriesList = new ArrayList<>();
    for (int seriesIdx = 0; seriesIdx < campFile.getSeriesBlocks().length; ++seriesIdx) {
      Wc1CampSeriesBlock seriesBlock = campFile.getSeriesBlocks()[seriesIdx];

      Wc1Series series = dozer.map(seriesBlock, Wc1Series.class);
      series.setId(UUID.randomUUID().toString());
      series.setSeriesNr(seriesIdx + 1);

      List<Wc1Mission> missionList = new ArrayList<>();
      for (int missionIdx = 0; missionIdx < seriesBlock.getNrOfMissions(); ++missionIdx) {
        Wc1CampMissionData missionData = seriesBlock.getMissionData()[missionIdx];

        Wc1Mission mission = dozer.map(missionData, Wc1Mission.class);
        mission.setId(UUID.randomUUID().toString());
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

  public Wc1GameFiles campaignToBinary(Wc1Campaign source) {
    Wc1CampFile campFile = generateCampFile(source);
    Wc1BriefingFile briefingFile = generateBriefingFile(source);
    Wc1ModuleFile moduleFile = generateModuleFile(source);

    return new Wc1GameFiles(moduleFile, campFile, briefingFile);
  }

  private Wc1CampFile generateCampFile(Wc1Campaign source) {
    Wc1CampFile sink = new Wc1CampFile();
    Wc1CampSeriesBlock[] seriesBlocks = new Wc1CampSeriesBlock[13];
    for (int seriesIdx = 0; seriesIdx < 13; ++seriesIdx) {
      Wc1Series series = source.getSeries().get(seriesIdx);

      Wc1CampSeriesBlock seriesBlock;
      if (seriesIdx < source.getSeries().size()) {
        seriesBlock = dozer.map(series, Wc1CampSeriesBlock.class);
        seriesBlock.setNrOfMissions(series.getNrOfMissions());
      } else {
        seriesBlock = Wc1CampSeriesBlock.EMPTY;
      }

      Wc1CampMissionData[] missionDataArray = new Wc1CampMissionData[4];
      for (int missionIdx = 0; missionIdx < 4; ++missionIdx) {
        Wc1CampMissionData missionData;
        if (missionIdx < series.getMissions().size()) {
          Wc1Mission mission = series.getMissions().get(missionIdx);
          missionData = dozer.map(mission, Wc1CampMissionData.class);
        } else {
          missionData = Wc1CampMissionData.EMPTY;
        }
        missionDataArray[missionIdx] = missionData;
      }
      seriesBlock.setMissionData(missionDataArray);

      seriesBlocks[seriesIdx] = seriesBlock;
    }

    sink.setSeriesBlocks(seriesBlocks);

    return sink;
  }

  private Wc1BriefingFile generateBriefingFile(Wc1Campaign source) {
    return null;
  }

  private Wc1ModuleFile generateModuleFile(Wc1Campaign source) {
    return null;
  }

  private CampaignTransformer() {
  }

  public static CampaignTransformer getInstance() {
    return instance;
  }
}
