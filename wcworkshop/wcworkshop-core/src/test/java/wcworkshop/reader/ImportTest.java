package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1GameFiles;
import wcworkshop.core.binary.Wc1ModuleFile;
import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.reader.Wc1BriefingReader;
import wcworkshop.core.reader.Wc1CampReader;
import wcworkshop.core.reader.Wc1ModuleReader;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.transformer.CampaignTransformer;

public class ImportTest {

  private CampaignTransformer campaignTransformer = CampaignTransformer.getInstance();
  private ProjectService projectService = ProjectService.getInstance();

  @Test
  public void transformToJson() {
    String ext = "000";

    Wc1ModuleFile moduleFile = Wc1ModuleReader.getInstance().read(ext);
    Wc1CampFile campFile = Wc1CampReader.getInstance().read(ext);
    Wc1BriefingFile briefingFile = Wc1BriefingReader.getInstance().read(ext);

    Wc1GameFiles gameFiles = new Wc1GameFiles(moduleFile, campFile, briefingFile);
    Wc1Campaign campaign = campaignTransformer.binaryToCampaign(gameFiles);

    Project newProject = new Project();
    newProject.setId("d0ef5747-9a29-410c-ace7-f7dda5d7d0a0");
    newProject.setOwner("delMar");
    newProject.setTitle("Wing Commander");
    newProject.setCampaign(campaign);

    projectService.saveProject(newProject);

    System.out.println("done");
  }
}
