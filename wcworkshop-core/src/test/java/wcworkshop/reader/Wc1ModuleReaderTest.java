package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.data.Wc1ModuleData;
import wcworkshop.core.reader.Wc1ModuleReader;

public class Wc1ModuleReaderTest {

  private Wc1ModuleReader reader = new Wc1ModuleReader();

  @Test
  public void readFile() {
    String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/MODULE.000";
    Wc1ModuleData moduleData = reader.readModuleFile(path);
  }

}
