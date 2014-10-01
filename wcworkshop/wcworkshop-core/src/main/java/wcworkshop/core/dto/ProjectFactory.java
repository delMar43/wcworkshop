package wcworkshop.core.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProjectFactory {
  private static final ProjectFactory instance = new ProjectFactory();

  private ProjectFactory() {
  }

  public Project createProject(String owner, String title, List<String> languages, Map<String, String> description, String website,
      EngineType engineType) {
    Project project = new Project();
    project.setId(UUID.randomUUID().toString());
    project.setOwner(owner);
    project.setTitle(title);
    project.setDescriptions(description);
    project.setWebsite(website);
    project.setEngineType(engineType);

    Wc1Campaign campaign = new Wc1Campaign();
    List<Wc1Series> seriesList = new ArrayList<>();
    Wc1Series series = new Wc1Series();
    series.setId(UUID.randomUUID().toString());

    List<Wc1Mission> missionList = new ArrayList<>();
    Wc1Mission mission = new Wc1Mission();
    mission.setId(UUID.randomUUID().toString());

    List<Wc1NavPoint> navPoints = new ArrayList<>();
    Wc1NavPoint navPoint = new Wc1NavPoint();
    navPoint.setId(UUID.randomUUID().toString());
    navPoints.add(navPoint);
    mission.setNavPoints(navPoints);

    missionList.add(mission);

    series.setMissions(missionList);

    seriesList.add(series);
    campaign.setSeries(seriesList);

    project.setCampaign(campaign);

    return project;
  }

  public static ProjectFactory getInstance() {
    return instance;
  }
}
