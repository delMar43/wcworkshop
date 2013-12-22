package wcworkshop.core.service;

import java.util.List;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.repo.ProjectRepo;

public class ProjectService {
  private static final ProjectService instance = new ProjectService();

  private ProjectRepo projectRepo = ProjectRepo.getInstance();

  private ProjectService() {
  }

  public List<Project> listProjects(String username) {
    return projectRepo.loadProjects(username);
  }

  public void saveProject(Project project) {
    projectRepo.saveProject(project);
  }

  public Project loadProject(String username, String projectId) {
    return projectRepo.loadProject(username, projectId);
  }

  public Wc1Series loadSeries(String username, String projectId, String seriesId) {
    Project project = loadProject(username, projectId);
    for (Wc1Series series : project.getCampaign().getSeries()) {
      if (series.getId().equals(seriesId)) {
        return series;
      }
    }
    throw new RuntimeException("Unable to find series");
  }

  public static ProjectService getInstance() {
    return instance;
  }

}
