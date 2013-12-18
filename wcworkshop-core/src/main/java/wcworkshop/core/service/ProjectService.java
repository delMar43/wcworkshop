package wcworkshop.core.service;

import wcworkshop.core.dto.Project;
import wcworkshop.core.repo.ProjectRepo;

public class ProjectService {
  private static final ProjectService instance = new ProjectService();

  private ProjectRepo projectRepo = ProjectRepo.getInstance();

  private ProjectService() {
  }

  public void saveProject(Project project) {
    projectRepo.saveProject(project);
  }

  public Project loadProject(String username, String projectTitle) {
    return projectRepo.loadProject(username, projectTitle);
  }

  public static ProjectService getInstance() {
    return instance;
  }

}
