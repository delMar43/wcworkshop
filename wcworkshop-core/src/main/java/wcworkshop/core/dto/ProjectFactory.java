package wcworkshop.core.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProjectFactory {
  private static final ProjectFactory instance = new ProjectFactory();

  private ProjectFactory() {
  }

  public Project createProject(String owner, String title, List<String> languages, Map<String, String> description, String website, EngineType engineType) {
    Project project = new Project();
    project.setOwner(owner);
    project.setTitle(title);
    project.setDescriptions(description);
    project.setWebsite(website);
    project.setEngineType(engineType);

    Wc1Campaign campaign = new Wc1Campaign();
    List<Wc1Series> series = new ArrayList<>();
    series.add(new Wc1Series(UUID.randomUUID().toString()));
    campaign.setSeries(series);

    return project;
  }

  public static ProjectFactory getInstance() {
    return instance;
  }
}
