package wcworkshop.core.repo;

import java.util.List;

import wcworkshop.core.dto.Project;

public class ProjectRepo extends AbstractJsonRepo<Project> {
  private static final ProjectRepo instance = new ProjectRepo();

  private ProjectRepo() {
  }

  public void saveProject(Project project) {
    writeFile(project.getOwner(), project.getId(), project);
  }

  public Project loadProject(String owner, String id) {
    Project project = loadFile(owner, id, Project.class);
    return project;
  }

  public boolean projectTitleExists(String owner, String title) {
    return false;
  }

  public List<Project> loadProjects(String username) {
    return loadFiles(username, Project.class);
  }

  public static ProjectRepo getInstance() {
    return instance;
  }

}
