package wcworkshop.core.binary;

public class Wc1BriefingFuneralData {

  private int[] offsets;
  private Wc1BriefingCutsceneSetting[] funeralCutsceneSettings;
  private Wc1BriefingCutsceneScript funeralCutsceneScript;

  public int[] getOffsets() {
    return offsets;
  }

  public void setOffsets(int[] offsets) {
    this.offsets = offsets;
  }

  public Wc1BriefingCutsceneSetting[] getFuneralCutsceneSettings() {
    return funeralCutsceneSettings;
  }

  public void setFuneralCutsceneSettings(Wc1BriefingCutsceneSetting[] funeralCutsceneSettings) {
    this.funeralCutsceneSettings = funeralCutsceneSettings;
  }

  public Wc1BriefingCutsceneScript getFuneralCutsceneScript() {
    return funeralCutsceneScript;
  }

  public void setFuneralCutsceneScript(Wc1BriefingCutsceneScript funeralCutsceneScript) {
    this.funeralCutsceneScript = funeralCutsceneScript;
  }

}
