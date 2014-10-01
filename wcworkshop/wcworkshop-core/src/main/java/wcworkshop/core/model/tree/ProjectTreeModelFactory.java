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

  public ProjectNodeData createProjectNode(Project project) {
    ProjectNodeData projectNode = new ProjectNodeData(project.getId(), project.getTitle());
    Wc1Campaign campaign = project.getCampaign();

    Map<String, String> data = new HashMap<>();
    data.put("projectId", project.getId());

    for (Wc1Series series : campaign.getSeries()) {
      data.put("seriesId", series.getId());
      data.put("type", "series");

      String seriesLabel = "Ser " + series.getSeriesNr() + " [" + series.getSystemName() + "]";
      SeriesNodeData seriesNode = new SeriesNodeData(series.getId(), seriesLabel, data);
      projectNode.addSeriesNode(series.getId(), seriesNode);

      int missionCount = 1;
      for (Wc1Mission mission : series.getMissions()) {
        data.put("missionId", mission.getId());
        data.put("type", "mission");

        String missionLabel = "Mis " + missionCount + " [" + mission.getWingName() + "]";
        MissionNodeData missionNode = new MissionNodeData(mission.getId(), missionLabel, data);
        seriesNode.addMissionNode(series.getId() + "_" + missionCount, missionNode);
        ++missionCount;

        missionNode.setCutscenesNode(createCutscenesNode(data, mission, seriesLabel + "/" + missionLabel + "/"));
        missionNode.setNavPointsNode(createNavPointsNode(data, mission));
      }
    }

    return projectNode;
  }

  private CutscenesNodeData createCutscenesNode(Map<String, String> data, Wc1Mission mission, String titlePrefix) {
    CutscenesNodeData result = new CutscenesNodeData(mission.getId() + "_cut", data);
    data.put("type", "cutscene");

    data.put("cutsceneType", "BRIEFING");
    result.addCutsceneNode(new CutsceneNodeData(result.getKey() + "_briefing", "Briefing", titlePrefix + "Briefing", data));
    data.put("cutsceneType", "DEBRIEFING");
    result.addCutsceneNode(new CutsceneNodeData(result.getKey() + "_debriefing", "Debriefing", titlePrefix + "Debriefing", data));
    data.put("cutsceneType", "SHOTGLASS");
    result.addCutsceneNode(new CutsceneNodeData(result.getKey() + "_shotglass", "Shotglass", titlePrefix + "Shotglass", data));
    data.put("cutsceneType", "LEFT_SEAT");
    result.addCutsceneNode(new CutsceneNodeData(result.getKey() + "_left", "Left", titlePrefix + "Left", data));
    data.put("cutsceneType", "RIGHT_SEAT");
    result.addCutsceneNode(new CutsceneNodeData(result.getKey() + "_right", "Right", titlePrefix + "Right", data));

    return result;
  }

  private NavPointsNodeData createNavPointsNode(Map<String, String> data, Wc1Mission mission) {
    NavPointsNodeData navPointsNode = new NavPointsNodeData(mission.getId() + "_nav");

    int navPointIdx = 0;
    for (Wc1NavPoint navPoint : mission.getNavPoints()) {
      data.put("navPointId", navPoint.getId());
      data.put("type", "navPoint");

      NavPointNodeData navPointNode = new NavPointNodeData(navPoint.getId(), navPointIdx++ + " [" + navPoint.getId() + "]", data);
      navPointsNode.addNavPointNode(navPointNode);
    }

    return navPointsNode;
  }

  public static ProjectTreeModelFactory getInstance() {
    return instance;
  }
}
