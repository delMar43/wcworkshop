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

    for (Wc1Series series : campaign.getSeries()) {
      Map<String, String> data = new HashMap<>();
      data.put("projectId", project.getId());

      SeriesNode seriesNode = new SeriesNode(series.getId(), "Ser " + series.getSeriesNr() + " [" + series.getSystemName() + "]", data);
      projectNode.addSeriesNode(series.getId(), seriesNode);

      int missionCount = 1;
      for (Wc1Mission mission : series.getMissions()) {
        MissionNode missionNode = new MissionNode(mission.getId(), "Mis " + missionCount + " [" + mission.getWingName() + "]");
        seriesNode.addMissionNode(series.getId() + "_" + missionCount, missionNode);
        ++missionCount;

        int navPointIdx = 0;
        for (Wc1NavPoint navPoint : mission.getNavPoints()) {
          NavPointNode navPointNode = new NavPointNode(navPoint.getId(), navPointIdx++ + " [" + navPoint.getId() + "]");
          missionNode.addNavPointNode(navPointNode);
        }
      }
    }

    return projectNode;
  }

  public static ProjectTreeModelFactory getInstance() {
    return instance;
  }
}
