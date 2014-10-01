package wcworkshop.core.service;

import java.util.List;

import wcworkshop.core.binary.Wc1BriefingFuneralData;
import wcworkshop.core.binary.Wc1BriefingHalcyonData;
import wcworkshop.core.binary.Wc1BriefingMedalCeremonyData;

public class Wc1Cutscenes {
  private Wc1BriefingFuneralData funeralData;
  private Wc1BriefingHalcyonData halcyonData;
  private Wc1BriefingMedalCeremonyData medalCeremonyData;
  private List<Wc1MissionCutscenes> missionCutscenes;

  public List<Wc1MissionCutscenes> getMissionCutscenes() {
    return missionCutscenes;
  }

  public void setMissionCutscenes(List<Wc1MissionCutscenes> missionCutscenes) {
    this.missionCutscenes = missionCutscenes;
  }

  public Wc1BriefingFuneralData getFuneralData() {
    return funeralData;
  }

  public void setFuneralData(Wc1BriefingFuneralData funeralData) {
    this.funeralData = funeralData;
  }

  public Wc1BriefingHalcyonData getHalcyonData() {
    return halcyonData;
  }

  public void setHalcyonData(Wc1BriefingHalcyonData halcyonData) {
    this.halcyonData = halcyonData;
  }

  public Wc1BriefingMedalCeremonyData getMedalCeremonyData() {
    return medalCeremonyData;
  }

  public void setMedalCeremonyData(Wc1BriefingMedalCeremonyData medalCeremonyData) {
    this.medalCeremonyData = medalCeremonyData;
  }

}
