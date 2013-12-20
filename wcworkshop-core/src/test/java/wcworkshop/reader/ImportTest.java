package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1ModuleFile;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.reader.Wc1BriefingReader;
import wcworkshop.core.reader.Wc1CampReader;
import wcworkshop.core.reader.Wc1ModuleReader;
import wcworkshop.core.transformer.CampaignTransformer;

public class ImportTest {

  private CampaignTransformer campaignTransformer = CampaignTransformer.getInstance();

  @Test
  public void transformToJson() {
    String ext = "000";

    Wc1ModuleFile moduleFile = Wc1ModuleReader.getInstance().read(ext);
    Wc1CampFile campFile = Wc1CampReader.getInstance().read(ext);
    Wc1BriefingFile briefingFile = Wc1BriefingReader.getInstance().read(ext);

    Wc1Campaign campaign = campaignTransformer.binaryToCampaign(moduleFile, briefingFile, campFile);
    System.out.println("done");
  }
}
