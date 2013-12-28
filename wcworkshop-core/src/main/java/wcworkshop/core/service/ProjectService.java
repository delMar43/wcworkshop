package wcworkshop.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1GameFiles;
import wcworkshop.core.binary.Wc1ModuleFile;
import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.model.SeriesModel;
import wcworkshop.core.reader.Wc1BriefingReader;
import wcworkshop.core.reader.Wc1CampReader;
import wcworkshop.core.reader.Wc1ModuleReader;
import wcworkshop.core.repo.ProjectRepo;
import wcworkshop.core.transformer.CampaignTransformer;

public class ProjectService {
  private static final ProjectService instance = new ProjectService();

  private ProjectRepo projectRepo = ProjectRepo.getInstance();
  private CampaignTransformer campaignTransformer = CampaignTransformer.getInstance();

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

  public Wc1Mission loadMission(String username, String projectId, String missionId) {
    Project project = loadProject(username, projectId);
    return findMission(project, missionId);
  }

  private Wc1Mission findMission(Project project, String missionId) {
    for (Wc1Series series : project.getCampaign().getSeries()) {
      for (Wc1Mission mission : series.getMissions()) {
        if (mission.getId().equals(missionId)) {
          return mission;
        }
      }
    }
    throw new RuntimeException("Unable to find series");
  }

  public void updateSeries(String username, String projectId, Wc1Series series) {
    Project project = loadProject(username, projectId);

    int seriesIndex = findSeriesIndex(project, series.getId());
    Wc1Series oldSeries = project.getCampaign().getSeries().get(seriesIndex);
    BeanUtils.copyProperties(series, oldSeries, new String[] { "missions" });

    saveProject(project);
  }

  private int findSeriesIndex(Project project, String idToFind) {
    List<Wc1Series> seriesList = project.getCampaign().getSeries();
    for (int idx = 0; idx < seriesList.size(); ++idx) {
      if (seriesList.get(idx).getId().equals(idToFind)) {
        return idx;
      }
    }
    throw new RuntimeException("Unable to find index of id " + idToFind);
  }

  public void updateCutscene(String username, String projectId, String missionId, Wc1Cutscene cutscene, Wc1CutsceneType cutsceneType) {
    Project project = loadProject(username, projectId);

    Wc1Mission oldMission = findMission(project, missionId);
    oldMission.getCutscenes().put(cutsceneType, cutscene);

    saveProject(project);
  }

  public void importProjectFromBinaries(String username) {
    String ext = "000";

    Wc1ModuleFile moduleFile = Wc1ModuleReader.getInstance().read(ext);
    Wc1CampFile campFile = Wc1CampReader.getInstance().read(ext);
    Wc1BriefingFile briefingFile = Wc1BriefingReader.getInstance().read(ext);

    Wc1GameFiles gameFiles = new Wc1GameFiles(moduleFile, campFile, briefingFile);
    Wc1Campaign campaign = campaignTransformer.binaryToCampaign(gameFiles);

    Project newProject = new Project();
    newProject.setId(UUID.randomUUID().toString());
    newProject.setOwner(username);
    String titlePrefix;
    if (username.endsWith("s")) {
      titlePrefix = "'";
    } else {
      titlePrefix = "'s";
    }
    titlePrefix = username + titlePrefix;

    newProject.setTitle(titlePrefix + " Wing Commander");
    newProject.setCampaign(campaign);

    saveProject(newProject);
  }

  public List<SeriesModel> listAllSeries(String username, String projectId, String campaignId) {
    Project project = loadProject(username, projectId);

    List<SeriesModel> result = new ArrayList<>();
    result.add(new SeriesModel(-1, "none"));
    for (Wc1Series series : project.getCampaign().getSeries()) {
      result.add(new SeriesModel(series.getSeriesNr(), series.getSystemName()));
    }

    return result;
  }

  public static ProjectService getInstance() {
    return instance;
  }

}
