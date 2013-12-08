package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1BriefingDataReaderTest {

  //  private Wc1BriefingReader reader = new Wc1BriefingReader();
  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @Test
  public void readFile() {
    Mapping mapper = mappingFactory.createMapping("wc1.briefing.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "BRIEFING.000");
    Wc1BriefingFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1BriefingFile.class);

    System.out.println("done");
    //    Wc1BriefingData briefingData = reader.readBriefingFile(path);

  }
}
