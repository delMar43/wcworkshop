package wcworkshop.core.model.tree;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1Series;

public class ProjectTreeModelFactory {

  private static final ProjectTreeModelFactory instance = new ProjectTreeModelFactory();

  private ProjectTreeModelFactory() {
  }

  public ProjectNode createProjectNode(Project project) {
    ProjectNode projectNode = new ProjectNode(project.getTitle());
    Wc1Campaign campaign = project.getCampaign();

    for (Wc1Series series : campaign.getSeries()) {
      SeriesNode seriesNode = new SeriesNode(series.getId() + " [" + series.getSystemName() + "]");
      projectNode.addSeriesNode(series.getId(), seriesNode);

      int missionCount = 1;
      for (Wc1Mission mission : series.getMissions()) {
        MissionNode missionNode = new MissionNode(missionCount + " [" + mission.getWingName() + "]");
        seriesNode.addMissionNode(series.getId() + "_" + missionCount, missionNode);
        ++missionCount;
      }
    }

    return projectNode;
  }

  public static ProjectTreeModelFactory getInstance() {
    return instance;
  }
}
