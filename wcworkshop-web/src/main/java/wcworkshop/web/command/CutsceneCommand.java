package wcworkshop.web.command;

import java.util.List;

import wcworkshop.core.dto.Wc1CutsceneScreen;

public class CutsceneCommand {

  private String projectId;
  private String missionId;
  private String cutsceneType;
  private List<Wc1CutsceneScreen> screens;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public List<Wc1CutsceneScreen> getScreens() {
    return screens;
  }

  public void setScreens(List<Wc1CutsceneScreen> screens) {
    this.screens = screens;
  }

  public String getCutsceneType() {
    return cutsceneType;
  }

  public void setCutsceneType(String cutsceneType) {
    this.cutsceneType = cutsceneType;
  }

  public String getMissionId() {
    return missionId;
  }

  public void setMissionId(String missionId) {
    this.missionId = missionId;
  }
}
