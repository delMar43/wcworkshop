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
import wcworkshop.core.binary.Wc1CampBarData;
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
import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneCommand;
import wcworkshop.core.dto.Wc1CutsceneForeground;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.dto.Wc1ShipAssignment;
import wcworkshop.core.service.Wc1CutsceneType;

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
        int totalIdx = seriesIdx * 4 + missionIdx;
        Wc1CampBarData barData = campFile.getBarData()[totalIdx];
        Wc1CampMissionData missionData = seriesBlock.getMissionData()[missionIdx];

        Wc1Mission mission = dozer.map(missionData, Wc1Mission.class);
        mission.setId(UUID.randomUUID().toString());
        mission.setLeftPilot(barData.getLeftSeat());
        mission.setRightPilot(barData.getRightSeat());
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
      Wc1BriefingCutsceneSetting setting = cutsceneSettings[idx];
      if (setting.getForeground() == Wc1CutsceneForeground.END_CONVERSATION.getValue()) {
        break;
      }
      Wc1CutsceneScreen screen = new Wc1CutsceneScreen();
      screen.setSequence(idx + 1);

      screen.setBackground(setting.getBackground());
      screen.setForeground(setting.getForeground());
      screen.setTextColor(setting.getFontColor());
      screen.setUnknown(setting.getUnknown());

      if (cutsceneScript.getScriptBytes().length >= 2) {
        byte[] commandBytes = getBytes(cutsceneScript, setting.getCommandOffset());
        screen.setCommands(extractCommands(commandBytes));

        String string = getNullTerminatedString(cutsceneScript, setting.getTextOffset());
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

  private List<Wc1CutsceneCommand> extractCommands(byte[] commandBytes) {
    List<Wc1CutsceneCommand> result = new ArrayList<>();

    Wc1CutsceneCommand cmd = new Wc1CutsceneCommand();
    StringBuilder currentParameter = null;
    boolean isFirst = true;
    for (byte command : commandBytes) {
      if (isFirst || isNonPrintable(command)) {
        cmd = new Wc1CutsceneCommand();
        result.add(cmd);
        cmd.setCode(command);

      } else if (isSeparator(command)) {
        if (currentParameter != null) {
          cmd.appendParameter(currentParameter.toString());
        }
        currentParameter = new StringBuilder();

      } else { //should be a parameter then
        if (currentParameter == null) {
          currentParameter = new StringBuilder();
        }
        currentParameter.append(new String(new byte[] { command }));
      }
      isFirst = false;
    }

    return result;
  }

  private boolean isNonPrintable(byte command) {
    return command <= 0x1F;
  }

  private boolean isSeparator(byte command) {
    return command == (byte) 0x2C;
  }

  private byte[] getBytes(Wc1BriefingCutsceneScript script, int from) {
    byte[] bytes = script.getScriptBytes();

    int to;
    for (to = from; to < bytes.length; ++to) {
      if (bytes[to] == (byte) 0) {
        break;
      }
    }

    return Arrays.copyOfRange(script.getScriptBytes(), from, to);
  }

  private String getNullTerminatedString(Wc1BriefingCutsceneScript cutsceneScript, int from) {
    try {
      String string = new String(getBytes(cutsceneScript, from));
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
    Wc1BriefingFile sink = new Wc1BriefingFile();
    Wc1BriefingMissionData[] missionDataArray = new Wc1BriefingMissionData[52];

    for (int seriesIdx = 0; seriesIdx < 13; ++seriesIdx) {
      Wc1Series series = source.getSeries().get(seriesIdx);
      for (int missionIdx = 0; missionIdx < 4; ++missionIdx) {
        Wc1BriefingMissionData missionData;
        if (missionIdx < series.getMissions().size()) {
          Wc1Mission mission = series.getMissions().get(missionIdx);
          missionData = new Wc1BriefingMissionData();

          for (Wc1CutsceneType cutsceneType : Wc1CutsceneType.values()) {
            fillMissionData(missionData, mission.getCutscenes(), cutsceneType);
          }

        } else {
          missionData = Wc1BriefingMissionData.EMPTY;
        }

        int totalIdx = seriesIdx * 4 + missionIdx;
        missionDataArray[totalIdx] = missionData;
      }
    }
    sink.setMissionData(missionDataArray);

    return sink;
  }

  private void fillMissionData(Wc1BriefingMissionData missionData, Map<Wc1CutsceneType, Wc1Cutscene> cutscenes, Wc1CutsceneType cutsceneType) {
    Wc1Cutscene cutscene = cutscenes.get(cutsceneType);
    fromModelToBinaryBeans(missionData, cutscene, cutsceneType);
  }

  private void fromModelToBinaryBeans(Wc1BriefingMissionData missionData, Wc1Cutscene cutscene, Wc1CutsceneType cutsceneType) {
    Wc1BriefingCutsceneSetting[] settings = new Wc1BriefingCutsceneSetting[cutscene.getScreens().size() + 1]; //+1 because we add "end-conversation" flag
    Wc1BriefingCutsceneScript script = new Wc1BriefingCutsceneScript();

    fillSettingsAndScripts(cutscene, settings, script);

    if (cutsceneType == Wc1CutsceneType.BRIEFING) {
      missionData.setBriefingCutsceneSettings(settings);
      missionData.setBriefingCutsceneScript(script);
    } else if (cutsceneType == Wc1CutsceneType.DEBRIEFING) {
      missionData.setDebriefingCutsceneSettings(settings);
      missionData.setDebriefingCutsceneScript(script);
    } else if (cutsceneType == Wc1CutsceneType.SHOTGLASS) {
      missionData.setShotglassCutsceneSettings(settings);
      missionData.setShotglassCutsceneScript(script);
    } else if (cutsceneType == Wc1CutsceneType.LEFT_SEAT) {
      missionData.setLeftSeatCutsceneSettings(settings);
      missionData.setLeftSeatCutsceneScript(script);
    } else if (cutsceneType == Wc1CutsceneType.RIGHT_SEAT) {
      missionData.setRightSeatCutsceneSettings(settings);
      missionData.setRightSeatCutsceneScript(script);
    }
  }

  private void fillSettingsAndScripts(Wc1Cutscene cutscene, Wc1BriefingCutsceneSetting[] settings, Wc1BriefingCutsceneScript script) {
    StringBuilder scriptString = new StringBuilder("\0");
    int idx = 0;
    int offset = 1;
    for (Wc1CutsceneScreen screen : cutscene.getScreens()) {
      Wc1BriefingCutsceneSetting setting = new Wc1BriefingCutsceneSetting();
      setting.setForeground(screen.getForeground());
      setting.setBackground(screen.getBackground());
      setting.setFontColor(screen.getTextColor());
      setting.setUnknown(screen.getUnknown());

      String commandString = generateCommandString(screen.getCommands());

      setting.setCommandOffset((short) offset);
      offset += getLength(commandString) + 1;

      setting.setTextOffset((short) offset);
      offset += getLength(screen.getText()) + 1;

      setting.setPhoneticOffset((short) offset);
      offset += getLength(screen.getPhonetic()) + 1;

      setting.setFacialExpressionOffset((short) offset);
      offset += getLength(screen.getFacialExpression()) + 1;

      settings[idx++] = setting;

      if (setting.getForeground() == Wc1CutsceneForeground.END_CONVERSATION.getValue()) {
        break;
      }
      scriptString.append(commandString + "\0");
      scriptString.append(screen.getText() + "\0");
      scriptString.append(screen.getPhonetic() + "\0");
      scriptString.append(screen.getFacialExpression() + "\0");
    }

    settings[idx] = Wc1BriefingCutsceneSetting.END_CONVERSATION_SETTING;

    script.setScriptBytes(scriptString.toString().getBytes());
  }

  private String generateCommandString(List<Wc1CutsceneCommand> commands) {
    StringBuilder result;
    if (commands == null || commands.size() == 0) {
      result = new StringBuilder("");
    } else {
      result = new StringBuilder();
      for (Wc1CutsceneCommand command : commands) {
        result.append((char) command.getCode());
        result.append(command.getParameters());
        result.append(",");
      }
    }
    return result.toString();
  }

  private short getLength(String string) {
    short result;
    if (string != null) {
      result = (short) string.length();
    } else {
      result = 0;
    }
    return result;
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
