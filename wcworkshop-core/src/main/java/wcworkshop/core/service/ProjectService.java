package wcworkshop.core.service;

import wcworkshop.core.dto.Project;

public class ProjectService {
  private static final ProjectService instance = new ProjectService();

  private ProjectService() {
  }

  public Project loadProject(String username, String projectTitle) {
    // TODO Auto-generated method stub
    return null;
  }

  public static ProjectService getInstance() {
    return instance;
  }

}
