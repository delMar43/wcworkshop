package wcworkshop.web.factory;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.dto.Project;
import wcworkshop.core.model.tree.ProjectNode;
import wcworkshop.core.model.tree.ProjectTreeModelFactory;
import wcworkshop.core.service.ProjectService;

public class UiFactory {
  private static final UiFactory instance = new UiFactory();

  private ProjectTreeModelFactory projectTreeFactory = ProjectTreeModelFactory.getInstance();
  private ProjectService projectService = ProjectService.getInstance();

  public List<ProjectNode> loadProjectTree(String username) {
    List<Project> projects = projectService.listProjects(username);

    List<ProjectNode> result = new ArrayList<>();
    for (Project project : projects) {
      result.add(createProjectNode(project));
    }

    return result;
  }

  public ProjectNode createProjectNode(Project project) {
    ProjectNode projectNode = projectTreeFactory.createProjectNode(project);
    return projectNode;
  }

  private UiFactory() {
  }

  public static UiFactory getInstance() {
    return instance;
  }
}
