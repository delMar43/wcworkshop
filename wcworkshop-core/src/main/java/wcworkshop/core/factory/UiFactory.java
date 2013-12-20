package wcworkshop.core.factory;

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
      ProjectNode projectNode = projectTreeFactory.createProjectNode(project);
      result.add(projectNode);
    }

    return result;
  }

  private UiFactory() {
  }

  public static UiFactory getInstance() {
    return instance;
  }
}
