package wcworkshop.core.model.tree;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Series;

public class ProjectTreeModelFactory {

  private static final ProjectTreeModelFactory instance = new ProjectTreeModelFactory();

  private ProjectTreeModelFactory() {
  }

  private ProjectNode createProjectNode(Project project) {
    Wc1Campaign campaign = project.getCampaign();
    ProjectNode projectNode = new ProjectNode(campaign.getTitle());

    for (Wc1Series series : campaign.getSeries()) {
      SeriesNode seriesNode = new SeriesNode(series.getId());
      projectNode.addSeriesNode(series.getId(), seriesNode);
    }

    return projectNode;
  }

  public static ProjectTreeModelFactory getInstance() {
    return instance;
  }
}
