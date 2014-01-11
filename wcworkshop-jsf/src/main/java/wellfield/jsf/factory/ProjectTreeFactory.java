package wellfield.jsf.factory;

import java.util.HashMap;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1NavPoint;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.model.tree.CutsceneNode;
import wcworkshop.core.model.tree.CutscenesNode;
import wcworkshop.core.model.tree.MissionNode;
import wcworkshop.core.model.tree.NavPointNode;
import wcworkshop.core.model.tree.NavPointsNode;
import wcworkshop.core.model.tree.ProjectNode;
import wcworkshop.core.model.tree.SeriesNode;
import wcworkshop.core.service.ProjectService;

public class ProjectTreeFactory {
  private static final ProjectTreeFactory instance = new ProjectTreeFactory();
  private ProjectService projectService = ProjectService.getInstance();

  public TreeNode generateProjectTree(String username) {
    List<Project> projects = projectService.listProjects(username);

    TreeNode root = new DefaultTreeNode("root", null);
    for (Project project : projects) {
      TreeNode projectNode = createProjectNode(project, root);
      Wc1Campaign campaign = project.getCampaign();

      for (Wc1Series series : campaign.getSeries()) {
        TreeNode seriesNode = createSeriesNode(series, projectNode);

        int missionIdx = 0;
        for (Wc1Mission mission : series.getMissions()) {
          TreeNode missionNode = createMissionNode(mission, ++missionIdx, seriesNode);

          TreeNode cutscenesNode = createCutscenesNode(mission, missionNode);
          appendCutsceneNode(mission.getId() + "_briefing", "Briefing", cutscenesNode);
          appendCutsceneNode(mission.getId() + "Debriefing", "Debriefing", cutscenesNode);
          appendCutsceneNode(mission.getId() + "Shotglass", "Shotglass", cutscenesNode);
          appendCutsceneNode(mission.getId() + "Left", "Left", cutscenesNode);
          appendCutsceneNode(mission.getId() + "Right", "Right", cutscenesNode);

          TreeNode navPointsNode = createNavPointsNode(mission, missionNode);
          appendNavPointNodes(mission, navPointsNode);
        }
      }
    }
    return root;
  }

  private TreeNode createProjectNode(Project project, TreeNode parent) {
    return new DefaultTreeNode("project", new ProjectNode(project.getId(), project.getTitle()), parent);
  }

  private TreeNode createSeriesNode(Wc1Series series, TreeNode projectNode) {
    String nodeTitle = "Ser " + series.getSeriesNr() + " [" + series.getSystemName() + "]";
    return new DefaultTreeNode("series", new SeriesNode(series.getId(), nodeTitle, new HashMap()), projectNode);
  }

  private TreeNode createMissionNode(Wc1Mission mission, int missionIdx, TreeNode seriesNode) {
    String nodeTitle = "Mis " + missionIdx + " [" + mission.getWingName() + "]";
    return new DefaultTreeNode("mission", new MissionNode(mission.getId(), nodeTitle, new HashMap()), seriesNode);
  }

  private TreeNode createCutscenesNode(Wc1Mission mission, TreeNode missionNode) {
    return new DefaultTreeNode("default", new CutscenesNode(mission.getId() + "_cutscenes", new HashMap()), missionNode);
  }

  private void appendCutsceneNode(String id, String label, TreeNode cutscenesNode) {
    new DefaultTreeNode("cutscene", new CutsceneNode("id", label, label, new HashMap()), cutscenesNode);
  }

  private TreeNode createNavPointsNode(Wc1Mission mission, TreeNode missionNode) {
    return new DefaultTreeNode("default", new NavPointsNode(mission.getId() + "_navPoints"), missionNode);
  }

  private void appendNavPointNodes(Wc1Mission mission, TreeNode navPointsNode) {
    for (Wc1NavPoint navPoint : mission.getNavPoints()) {
      new DefaultTreeNode("navPoint", new NavPointNode(navPoint.getId(), navPoint.getText(), new HashMap()), navPointsNode);
    }
  }

  private ProjectTreeFactory() {
  }

  public static ProjectTreeFactory getInstance() {
    return instance;
  }
}
