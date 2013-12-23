package wcworkshop.core.transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import wcworkshop.core.binary.Wc1BriefingCutsceneScript;
import wcworkshop.core.binary.Wc1BriefingCutsceneSetting;
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
import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.dto.Wc1ShipAssignment;
import wcworkshop.core.service.Wc1CutsceneType;

public class CampaignTransformer {
  private static final CampaignTransformer instance = new CampaignTransformer();

  private Mapper dozer = new DozerBeanMapper();

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
    Wc1BriefingMissionData[] missionDataArray = briefingFile.getMissionData();

    int nrMissions = missionDataArray.length;
    int missionDataIdx = 0;

    for (int series = 0; series < 13; ++series) {
      for (int mission = 0; mission < 4; ++mission) {
        Wc1BriefingMissionData missionData = missionDataArray[missionDataIdx++];
        if (missionData == null) {
          continue;
        }

        Wc1Mission wc1mission = result.getSeries().get(series).getMissions().get(mission);

        Map<Wc1CutsceneType, Wc1Cutscene> cutscenes = extractCutscenes(missionData);
        wc1mission.setCutscenes(cutscenes);
        if (missionDataIdx >= nrMissions) {
          break;
        }
      }
      if (missionDataIdx >= nrMissions) {
        break;
      }
    }
  }

  private Map<Wc1CutsceneType, Wc1Cutscene> extractCutscenes(Wc1BriefingMissionData missionData) {
    Map<Wc1CutsceneType, Wc1Cutscene> result = new HashMap<>();

    Wc1Cutscene cutscene = extractCutscene(missionData.getBriefingCutsceneSettings(), missionData.getBriefingCutsceneScript());
    result.put(Wc1CutsceneType.BRIEFING, cutscene);

    cutscene = extractCutscene(missionData.getDebriefingCutsceneSettings(), missionData.getDebriefingCutsceneScript());
    result.put(Wc1CutsceneType.DEBRIEFING, cutscene);

    cutscene = extractCutscene(missionData.getShotglassCutsceneSettings(), missionData.getShotglassCutsceneScript());
    result.put(Wc1CutsceneType.SHOTGLASS, cutscene);

    cutscene = extractCutscene(missionData.getLeftSeatCutsceneSettings(), missionData.getLeftSeatCutsceneScript());
    result.put(Wc1CutsceneType.LEFT_SEAT, cutscene);

    cutscene = extractCutscene(missionData.getRightSeatCutsceneSettings(), missionData.getRightSeatCutsceneScript());
    result.put(Wc1CutsceneType.RIGHT_SEAT, cutscene);

    return result;
  }

  private Wc1Cutscene extractCutscene(Wc1BriefingCutsceneSetting[] cutsceneSettings, Wc1BriefingCutsceneScript cutsceneScript) {
    Wc1Cutscene result = new Wc1Cutscene();

    List<Wc1CutsceneScreen> screens = new ArrayList<>();

    for (int idx = 0; idx < cutsceneSettings.length; ++idx) {
      Wc1CutsceneScreen screen = new Wc1CutsceneScreen();

      Wc1BriefingCutsceneSetting setting = cutsceneSettings[idx];
      screen.setBackground(setting.getBackground());
      screen.setForeground(setting.getForeground());
      screen.setTextColor(setting.getFontColor());
      screen.setUnknown(setting.getUnknown());

      if (cutsceneScript.getScriptBytes().length >= 2) {
        String string = getNullTerminatedString(cutsceneScript, setting.getCommandOffset());
        screen.setCommands(string);

        string = getNullTerminatedString(cutsceneScript, setting.getTextOffset());
        screen.setText(string);

        string = getNullTerminatedString(cutsceneScript, setting.getPhoneticOffset());
        screen.setPhonetic(string);

        string = getNullTerminatedString(cutsceneScript, setting.getFacialExpressionOffset());
        screen.setFacialExpression(string);
      }

      screens.add(screen);
    }

    result.setScreens(screens);

    return result;
  }

  private String getNullTerminatedString(Wc1BriefingCutsceneScript cutsceneScript, int from) {
    byte[] bytes = cutsceneScript.getScriptBytes();

    int to;
    for (to = from; to < bytes.length; ++to) {
      if (bytes[to] == (byte) 0) {
        break;
      }
    }

    try {
      String string = new String(Arrays.copyOfRange(cutsceneScript.getScriptBytes(), from, to));
      return string;
    } catch (Exception e) {
      System.out.println();
      throw new RuntimeException(e.getMessage(), e);
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

          Wc1NavPoint navPoint = dozer.map(curNavPointData, Wc1NavPoint.class);

          Wc1ModuleNavPointMapData curNavPointMapData = navPointMapData[curNavIdx];
          dozer.map(curNavPointMapData, navPoint);

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
