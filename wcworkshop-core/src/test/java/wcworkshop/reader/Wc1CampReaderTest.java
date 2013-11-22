package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.reader.Wc1CampaignReader;

public class Wc1CampReaderTest {

  private Wc1CampaignReader reader = new Wc1CampaignReader();

  @Test
  public void readFile() {
    String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/CAMP.002";
    Wc1CampData camp = reader.readCampaignFile(path);
  }
}
