package wcworkshop.core.service;

import java.util.List;

public class Wc1Cutscenes {
  private byte[] funeralData;
  private byte[] halcyonData;
  private byte[] medalCeremonyData;
  private List<Wc1MissionCutscenes> missionCutscenes;

  public List<Wc1MissionCutscenes> getMissionCutscenes() {
    return missionCutscenes;
  }

  public void setMissionCutscenes(List<Wc1MissionCutscenes> missionCutscenes) {
    this.missionCutscenes = missionCutscenes;
  }

  public byte[] getFuneralData() {
    return funeralData;
  }

  public void setFuneralData(byte[] funeralData) {
    this.funeralData = funeralData;
  }

  public byte[] getHalcyonData() {
    return halcyonData;
  }

  public void setHalcyonData(byte[] halcyonData) {
    this.halcyonData = halcyonData;
  }

  public byte[] getMedalCeremonyData() {
    return medalCeremonyData;
  }

  public void setMedalCeremonyData(byte[] medalCeremonyData) {
    this.medalCeremonyData = medalCeremonyData;
  }

}
