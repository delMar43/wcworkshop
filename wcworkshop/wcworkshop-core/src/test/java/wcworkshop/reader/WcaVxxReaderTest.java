package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.WcaVxxReader;

public class WcaVxxReaderTest {

  private Configuration config = Configuration.getInstance();
  
  @Test
  public void test() {
    WcaVxxReader.getInstance().read(config.getResourcePath() + "ARROW.VGA");
  }
}
