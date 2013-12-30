package wcworkshop.core.model.tree;

import java.util.HashMap;
import java.util.Map;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;

public class ProjectTreeModelFactory {

  private static final ProjectTreeModelFactory instance = new ProjectTreeModelFactory();

  private ProjectTreeModelFactory() {
  }

  public ProjectNode createProjectNode(Project project) {
    ProjectNode projectNode = new ProjectNode(project.getId(), project.getTitle());
    Wc1Campaign campaign = project.getCampaign();

    Map<String, String> data = new HashMap<>();
    data.put("projectId", project.getId());

    for (Wc1Series series : campaign.getSeries()) {
      data.put("seriesId", series.getId());
      data.put("type", "series");

      SeriesNode seriesNode = new SeriesNode(series.getId(), "Ser " + series.getSeriesNr() + " [" + series.getSystemName() + "]", data);
      projectNode.addSeriesNode(series.getId(), seriesNode);

      int missionCount = 1;
      for (Wc1Mission mission : series.getMissions()) {
        data.put("missionId", mission.getId());
        data.put("type", "mission");

        MissionNode missionNode = new MissionNode(mission.getId(), "Mis " + missionCount + " [" + mission.getWingName() + "]", data);
        seriesNode.addMissionNode(series.getId() + "_" + missionCount, missionNode);
        ++missionCount;

        missionNode.setCutscenesNode(createCutscenesNode(data, mission));
        missionNode.setNavPointsNode(createNavPointsNode(data, mission));
      }
    }

    return projectNode;
  }

  private CutscenesNode createCutscenesNode(Map<String, String> data, Wc1Mission mission) {
    CutscenesNode result = new CutscenesNode(mission.getId() + "_cut", data);
    data.put("type", "cutscene");

    data.put("cutsceneType", "BRIEFING");
    result.addCutsceneNode(new CutsceneNode(result.getKey() + "_briefing", "Briefing", data));
    data.put("cutsceneType", "DEBRIEFING");
    result.addCutsceneNode(new CutsceneNode(result.getKey() + "_debriefing", "Debriefing", data));
    data.put("cutsceneType", "SHOTGLASS");
    result.addCutsceneNode(new CutsceneNode(result.getKey() + "_shotglass", "Shotglass", data));
    data.put("cutsceneType", "LEFT");
    result.addCutsceneNode(new CutsceneNode(result.getKey() + "_left", "Left", data));
    data.put("cutsceneType", "RIGHT");
    result.addCutsceneNode(new CutsceneNode(result.getKey() + "_right", "Right", data));

    return result;
  }

  private NavPointsNode createNavPointsNode(Map<String, String> data, Wc1Mission mission) {
    NavPointsNode navPointsNode = new NavPointsNode(mission.getId() + "_nav");

    int navPointIdx = 0;
    for (Wc1NavPoint navPoint : mission.getNavPoints()) {
      data.put("navPointId", navPoint.getId());
      data.put("type", "navPoint");

      NavPointNode navPointNode = new NavPointNode(navPoint.getId(), navPointIdx++ + " [" + navPoint.getId() + "]", data);
      navPointsNode.addNavPointNode(navPointNode);
    }

    return navPointsNode;
  }

  public static ProjectTreeModelFactory getInstance() {
    return instance;
  }
}
