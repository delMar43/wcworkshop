package wcworkshop.core.model.tree;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Series;

public class ProjectTreeModelFactory {

  private static final ProjectTreeModelFactory instance = new ProjectTreeModelFactory();

  private ProjectTreeModelFactory() {
  }

  public ProjectNode createProjectNode(Project project) {
    ProjectNode projectNode = new ProjectNode(project.getTitle());
    Wc1Campaign campaign = project.getCampaign();

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
