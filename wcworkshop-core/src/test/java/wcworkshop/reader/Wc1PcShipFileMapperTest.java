package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1PcShipFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1PcShipFileMapperTest {
  private Configuration config = Configuration.getInstance();
  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @Test
  public void read() {
    Mapping mapper = mappingFactory.createMapping("wc1.pcship.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "PCSHIP.V00");
    Wc1PcShipFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1PcShipFile.class);

    System.out.println("done");
  }
}
