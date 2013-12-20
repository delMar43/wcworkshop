package wcworkshop.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wcworkshop.core.binary.Wc1BriefingCutsceneScript;
import wcworkshop.core.binary.Wc1BriefingCutsceneSetting;
import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1BriefingMissionData;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1CutsceneReadService {
  private static final Logger logger = LoggerFactory.getLogger(Wc1CutsceneReadService.class);

  private static final Wc1CutsceneReadService instance = new Wc1CutsceneReadService();

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  private Wc1CutsceneReadService() {
  }

  public Wc1Cutscenes loadCutscenes(String campaign) {
    Mapping mapper = mappingFactory.createMapping("wc1.briefing.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "BRIEFING." + campaign);
    Wc1BriefingFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1BriefingFile.class);

    Wc1Cutscenes result = new Wc1Cutscenes();
    result.setFuneralData(file.getFuneralData());
    result.setHalcyonData(file.getHalcyon());
    result.setMedalCeremonyData(file.getMedalCeremony());

    // Wc1Cutscene funeralCutscene = extractCutscene(file.getFuneralData().getFuneralCutsceneSettings(), file.getFuneralData()
    // .getFuneralCutsceneScript());
    // result.setFuneralCutscene(funeralCutscene);

    int nrMissions = file.getMissionData().length;
    int missionDataIdx = 0;
    List<Wc1MissionCutscenes> allCutscenes = new ArrayList<>();
    for (int series = 0; series < 13; ++series) {
      for (int mission = 0; mission < 4; ++mission) {
        Wc1MissionCutscenes missionCutscenes = new Wc1MissionCutscenes();

        Wc1BriefingMissionData missionData = file.getMissionData()[missionDataIdx++];
        if (missionData == null) {
          allCutscenes.add(Wc1MissionCutscenes.EMPTY);
          continue;
        }
        Map<Wc1CutsceneType, Wc1Cutscene> cutscenes = extractCutscenes(missionData);
        missionCutscenes.setCutscenes(cutscenes);
        allCutscenes.add(missionCutscenes);
        if (missionDataIdx >= nrMissions) {
          break;
        }
      }
      if (missionDataIdx >= nrMissions) {
        break;
      }
    }
    result.setMissionCutscenes(allCutscenes);
    return result;
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

  public static Wc1CutsceneReadService getInstance() {
    return instance;
  }
}
