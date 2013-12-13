package wcworkshop.core.service;

import java.util.Map;

import wcworkshop.core.binary.Wc1BriefingCutsceneScript;
import wcworkshop.core.binary.Wc1BriefingCutsceneSetting;
import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1BriefingMissionData;
import wcworkshop.core.model.Wc1Cutscene;
import wcworkshop.core.model.Wc1CutsceneScreen;
import binmap.BinaryUtils;
import binmap.BinaryWriter;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1CutsceneWriteService {
  private static final Wc1CutsceneWriteService instance = new Wc1CutsceneWriteService();

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private BinaryWriter binaryWriter = BinaryWriter.getInstance();
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  private Wc1CutsceneWriteService() {
  }

  public byte[] generateCutscenes(Wc1Cutscenes source) {
    Wc1BriefingFile file = new Wc1BriefingFile();
    Wc1BriefingMissionData[] missionDataArray = new Wc1BriefingMissionData[source.getMissionCutscenes().size()];

    int missionIdx = 0;
    for (Wc1MissionCutscenes missionCutscenes : source.getMissionCutscenes()) {
      Wc1BriefingMissionData missionData = new Wc1BriefingMissionData();
      missionDataArray[missionIdx++] = missionData;

      Map<Wc1CutsceneType, Wc1Cutscene> cutscenes = missionCutscenes.getCutscenes();
      if (cutscenes == null) {
        continue;
      }

      for (Wc1CutsceneType cutsceneType : Wc1CutsceneType.values()) {
        fillMissionData(missionData, cutscenes, cutsceneType);
      }
    }

    file.setFuneralData(source.getFuneralData());
    file.setHalcyon(source.getHalcyonData());
    file.setMedalCeremony(source.getMedalCeremonyData());
    file.setMissionData(missionDataArray);

    Mapping mapping = mappingFactory.createMapping("wc1.briefing.mapping");
    byte[] newContents = binaryWriter.toDynamicBinary(file, mapping);

    int length = newContents.length;
    binaryUtils.copyIntoArray(binaryUtils.createIntegerBytes(length), newContents, 0);

    return newContents;
  }

  private void fillMissionData(Wc1BriefingMissionData missionData, Map<Wc1CutsceneType, Wc1Cutscene> cutscenes, Wc1CutsceneType cutsceneType) {
    Wc1Cutscene cutscene = cutscenes.get(cutsceneType);
    fromModelToBinaryBeans(missionData, cutscene, cutsceneType);
  }

  private void fromModelToBinaryBeans(Wc1BriefingMissionData missionData, Wc1Cutscene cutscene, Wc1CutsceneType cutsceneType) {
    Wc1BriefingCutsceneSetting[] settings = new Wc1BriefingCutsceneSetting[cutscene.getScreens().size()];
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
    StringBuilder scriptString = new StringBuilder();
    int idx = 0;
    int offset = 1;
    for (Wc1CutsceneScreen screen : cutscene.getScreens()) {
      Wc1BriefingCutsceneSetting setting = new Wc1BriefingCutsceneSetting();
      setting.setForeground(screen.getForeground());
      setting.setBackground(screen.getBackground());
      setting.setFontColor(screen.getTextColor());
      setting.setUnknown(screen.getUnknown());

      setting.setCommandOffset((short) offset);
      offset += getLength(screen.getCommands()) + 1;

      setting.setTextOffset((short) offset);
      offset += getLength(screen.getText()) + 1;

      setting.setPhoneticOffset((short) offset);
      offset += getLength(screen.getPhonetic()) + 1;

      setting.setFacialExpressionOffset((short) offset);
      offset += getLength(screen.getFacialExpression()) + 1;

      settings[idx++] = setting;

      if (setting.getForeground() == (byte) 0xFE) {
        break;
      }
      scriptString.append(screen.getCommands() + "\0");
      scriptString.append(screen.getText() + "\0");
      scriptString.append(screen.getPhonetic() + "\0");
      scriptString.append(screen.getFacialExpression() + "\0");
    }
    script.setScriptBytes(scriptString.toString().getBytes());
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

  private void appendToScriptString(StringBuilder scriptString, String content) {
    if (content == null) {
      content = "";
    }
    scriptString.append(content + "\0");
  }

  public static Wc1CutsceneWriteService getInstance() {
    return instance;
  }
}
