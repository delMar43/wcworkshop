package wcworkshop.core.service;

import java.util.List;

import wcworkshop.core.model.Wc1Cutscene;

public class Wc1Cutscenes {
  private Wc1Cutscene funeralCutscene;
  private List<Wc1MissionCutscenes> missionCutscenes;

  public List<Wc1MissionCutscenes> getMissionCutscenes() {
    return missionCutscenes;
  }

  public void setMissionCutscenes(List<Wc1MissionCutscenes> missionCutscenes) {
    this.missionCutscenes = missionCutscenes;
  }

  public Wc1Cutscene getFuneralCutscene() {
    return funeralCutscene;
  }

  public void setFuneralCutscene(Wc1Cutscene funeralCutscene) {
    this.funeralCutscene = funeralCutscene;
  }
}
