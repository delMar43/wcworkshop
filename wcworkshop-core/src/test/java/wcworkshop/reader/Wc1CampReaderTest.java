package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1CampReaderTest {

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @Test
  public void readFile() {
    //    String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/CAMP.002";
    //    Wc1CampData camp = reader.readCampaignFile(path);

    Mapping mapper = mappingFactory.createMapping("wc1.camp.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "CAMP.000");
    Wc1CampFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1CampFile.class);

    System.out.println("done");
  }
}
