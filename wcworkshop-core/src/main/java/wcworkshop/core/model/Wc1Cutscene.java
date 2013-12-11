package wcworkshop.core.model;

import java.util.List;

public class Wc1Cutscene {
  private List<Wc1CutsceneScreen> screens;

  public void setScreens(List<Wc1CutsceneScreen> screens) {
    this.screens = screens;
  }

  public List<Wc1CutsceneScreen> getScreens() {
    return screens;
  }
}
