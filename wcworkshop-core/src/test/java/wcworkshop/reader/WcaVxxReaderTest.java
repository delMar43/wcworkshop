package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1VgaFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class WcaVxxReaderTest {

  // private WcaVxxReader reader = WcaVxxReader.getInstance();
  private Configuration config = Configuration.getInstance();
  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @Test
  public void read() {
    // reader.read(config.getResourcePath() + "PLANETS.VGA");

    Mapping mapper = mappingFactory.createMapping("wc1.vga.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "PLANETS.VGA");
    Wc1VgaFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1VgaFile.class);

    System.out.println("done");
  }
}
