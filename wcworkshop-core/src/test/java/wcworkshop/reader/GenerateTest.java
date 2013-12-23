package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1GameFiles;
import wcworkshop.core.dto.Project;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.transformer.CampaignTransformer;

public class GenerateTest {

  private ProjectService projectService = ProjectService.getInstance();
  private CampaignTransformer campaignTransformer = CampaignTransformer.getInstance();

  @Test
  public void generate() {
    String projectId = "d0ef5747-9a29-410c-ace7-f7dda5d7d0a0";
    Project project = projectService.loadProject("delMar", projectId);

    Wc1GameFiles gameFiles = campaignTransformer.campaignToBinary(project.getCampaign());
    System.out.println("done");
  }
}
