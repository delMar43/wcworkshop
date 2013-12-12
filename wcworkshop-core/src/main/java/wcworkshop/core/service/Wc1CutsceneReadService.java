package wcworkshop.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wcworkshop.core.binary.Wc1BriefingCutsceneScript;
import wcworkshop.core.binary.Wc1BriefingCutsceneSetting;
import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1BriefingMissionData;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.model.Wc1Cutscene;
import wcworkshop.core.model.Wc1CutsceneScreen;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1CutsceneReadService {
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

    //    Wc1Cutscene funeralCutscene = extractCutscene(file.getFuneralData().getFuneralCutsceneSettings(), file.getFuneralData()
    //        .getFuneralCutsceneScript());
    //    result.setFuneralCutscene(funeralCutscene);

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

    String script = new String(cutsceneScript.getScriptBytes());
    List<Wc1CutsceneScreen> screens = extractCutsceneScreens(script);

    for (int idx = 0; idx < screens.size(); ++idx) {
      Wc1CutsceneScreen screen = screens.get(idx);
      Wc1BriefingCutsceneSetting setting = cutsceneSettings[idx];
      screen.setBackground(setting.getBackground());
      screen.setForeground(setting.getForeground());
      screen.setTextColor(setting.getFontColor());
      screen.setUnknown(setting.getUnknown());
    }

    result.setScreens(screens);

    return result;
  }

  private List<Wc1CutsceneScreen> extractCutsceneScreens(String source) {
    List<Wc1CutsceneScreen> result = new ArrayList<>();

    String[] parts = source.split("\0");
    for (int partIndex = 0; partIndex < parts.length; partIndex += 4) {
      Wc1CutsceneScreen screen = new Wc1CutsceneScreen();

      screen.setFacialExpression(parts[partIndex]);
      if (partIndex + 1 < parts.length) {
        screen.setCommands(parts[partIndex + 1]);
      }
      if (partIndex + 2 < parts.length) {
        screen.setText(parts[partIndex + 2]);
      }
      if (partIndex + 3 < parts.length) {
        screen.setPhonetic(parts[partIndex + 3]);
      }

      result.add(screen);
    }

    return result;
  }

  public static Wc1CutsceneReadService getInstance() {
    return instance;
  }
}
