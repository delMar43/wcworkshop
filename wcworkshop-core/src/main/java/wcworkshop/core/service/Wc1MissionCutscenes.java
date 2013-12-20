package wcworkshop.core.service;

import java.util.Map;

import wcworkshop.core.dto.Wc1Cutscene;

public class Wc1MissionCutscenes {

  public static final Wc1MissionCutscenes EMPTY = new Wc1MissionCutscenes();

  private Map<Wc1CutsceneType, Wc1Cutscene> cutscenes;

  public Map<Wc1CutsceneType, Wc1Cutscene> getCutscenes() {
    return cutscenes;
  }

  public void setCutscenes(Map<Wc1CutsceneType, Wc1Cutscene> cutscenes) {
    this.cutscenes = cutscenes;
  }
}
