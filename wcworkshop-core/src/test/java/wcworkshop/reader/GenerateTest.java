package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.dto.Project;
import wcworkshop.core.service.ProjectService;

public class GenerateTest {

  private ProjectService projectService = ProjectService.getInstance();

  @Test
  public void generate() {
    String projectId = "484b23e1-649d-4d00-ad2b-311106ab3890";
    Project project = projectService.loadProject("delMar", projectId);

  }
}
