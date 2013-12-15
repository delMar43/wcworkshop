package wcworkshop.core.util;

import org.junit.Test;

import wcworkshop.core.binary.Wc1VgaFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class LzwExtractorTest {
  private Configuration config = Configuration.getInstance();
  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private BinaryReader binaryReader = BinaryReader.getInstance();

  private LzwExtractor extractor = LzwExtractor.getInstance();

  @Test
  public void extract() {
    byte[] data = loadRleBytes();
    
    
  }

  private byte[] loadRleBytes() {
    Mapping mapper = mappingFactory.createMapping("wc1.vga.mapping");
    byte[] data = readerHelper.readFile(config.getResourcePath() + "ARROW.VGA");
    Wc1VgaFile file = binaryReader.toJava(data, mapper, Wc1VgaFile.class);

    return file.getBlocks()[0].getData();
  }
}
