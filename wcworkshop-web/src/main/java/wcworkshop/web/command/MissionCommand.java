package wcworkshop.web.command;

import wcworkshop.core.dto.Wc1Mission;

public class MissionCommand {

  private String projectId;
  private Wc1Mission mission;

  public MissionCommand() {
  }

  public MissionCommand(String projectId, Wc1Mission mission) {
    this.projectId = projectId;
    this.mission = mission;
  }

  public Wc1Mission getMission() {
    return mission;
  }

  public void setMission(Wc1Mission mission) {
    this.mission = mission;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }
}
