package wellfield.jsf.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.model.tree.CutsceneNodeData;
import wcworkshop.core.model.tree.CutscenesNodeData;
import wcworkshop.core.model.tree.MissionNodeData;
import wcworkshop.core.model.tree.NavPointNodeData;
import wcworkshop.core.model.tree.NavPointsNodeData;
import wcworkshop.core.model.tree.ProjectNodeData;
import wcworkshop.core.model.tree.SeriesNodeData;
import wcworkshop.core.service.ProjectService;

public class ProjectTreeFactory {
  private static final ProjectTreeFactory instance = new ProjectTreeFactory();
  private ProjectService projectService = ProjectService.getInstance();

  public TreeNode generateProjectTree(String username) {
    List<Project> projects = projectService.listProjects(username);

    Map<String, String> data = new HashMap<>();
    TreeNode root = new DefaultTreeNode("root", null);
    for (Project project : projects) {
      data.put("projectId", project.getId());
      TreeNode projectNode = createProjectNode(project, root);
      Wc1Campaign campaign = project.getCampaign();

      for (Wc1Series series : campaign.getSeries()) {
        data.put("seriesId", series.getId());
        TreeNode seriesNode = createSeriesNode(series, projectNode, data);

        int missionIdx = 0;
        for (Wc1Mission mission : series.getMissions()) {
          data.put("missionId", mission.getId());
          TreeNode missionNode = createMissionNode(mission, ++missionIdx, seriesNode, data);

          TreeNode cutscenesNode = createCutscenesNode(mission, missionNode);
          appendCutsceneNode(mission.getId() + "_Briefing", "Briefing", cutscenesNode, data);
          appendCutsceneNode(mission.getId() + "_Debriefing", "Debriefing", cutscenesNode, data);
          appendCutsceneNode(mission.getId() + "_Shotglass", "Shotglass", cutscenesNode, data);
          appendCutsceneNode(mission.getId() + "_Left", "Left", cutscenesNode, data);
          appendCutsceneNode(mission.getId() + "_Right", "Right", cutscenesNode, data);

          TreeNode navPointsNode = createNavPointsNode(mission, missionNode);
          appendNavPointNodes(mission, navPointsNode, data);
        }
      }
    }
    return root;
  }

  private TreeNode createProjectNode(Project project, TreeNode parent) {
    return new DefaultTreeNode("project", new ProjectNodeData(project.getId(), project.getTitle()), parent);
  }

  private TreeNode createSeriesNode(Wc1Series series, TreeNode projectNode, Map<String, String> data) {
    String nodeTitle = "Ser " + series.getSeriesNr() + " [" + series.getSystemName() + "]";
    return new DefaultTreeNode("series", new SeriesNodeData(series.getId(), nodeTitle, data), projectNode);
  }

  private TreeNode createMissionNode(Wc1Mission mission, int missionIdx, TreeNode seriesNode, Map<String, String> data) {
    String nodeTitle = "Mis " + missionIdx + " [" + mission.getWingName() + "]";
    return new DefaultTreeNode("mission", new MissionNodeData(mission.getId(), nodeTitle, data), seriesNode);
  }

  private TreeNode createCutscenesNode(Wc1Mission mission, TreeNode missionNode) {
    Map<String, String> data = new HashMap<>();

    return new DefaultTreeNode("default", new CutscenesNodeData(mission.getId() + "_cutscenes", data), missionNode);
  }

  private void appendCutsceneNode(String id, String label, TreeNode cutscenesNode, Map<String, String> data) {
    new DefaultTreeNode("cutscene", new CutsceneNodeData("id", label, label, data), cutscenesNode);
  }

  private TreeNode createNavPointsNode(Wc1Mission mission, TreeNode missionNode) {
    return new DefaultTreeNode("default", new NavPointsNodeData(mission.getId() + "_navPoints"), missionNode);
  }

  private void appendNavPointNodes(Wc1Mission mission, TreeNode navPointsNode, Map<String, String> data) {
    for (Wc1NavPoint navPoint : mission.getNavPoints()) {
      new DefaultTreeNode("navPoint", new NavPointNodeData(navPoint.getId(), navPoint.getText(), data), navPointsNode);
    }
  }

  private ProjectTreeFactory() {
  }

  public static ProjectTreeFactory getInstance() {
    return instance;
  }
}
