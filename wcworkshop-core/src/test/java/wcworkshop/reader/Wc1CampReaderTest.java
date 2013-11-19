package wcworkshop.reader;

import java.util.Arrays;

import org.junit.Test;

import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.reader.Wc1CampaignReader;

public class Wc1CampReaderTest {

  private Wc1CampaignReader reader = new Wc1CampaignReader();

  @Test
  public void readFile() {
    String path = "C:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/CAMP.000";
    Wc1CampData camp = reader.readCampaignFile(path);
    System.out.println("Filesize: " + camp.getFilesize());
    System.out.println("Block offsets: " + Arrays.toString(camp.getBlockOffsets().toArray()));
  }
}
