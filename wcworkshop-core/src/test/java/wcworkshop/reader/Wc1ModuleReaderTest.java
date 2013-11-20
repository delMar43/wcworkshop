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
    System.out.println("Filesize: " + moduleData.getFilesize());
    Integer[] array = moduleData.getBlockOffsets().toArray(new Integer[] {});
    System.out.println(array.length + " Block offsets: " + intArrayToHexString(array));
  }

  private String intArrayToHexString(Integer[] input) {
    if (input == null) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (int b : input) {
      result.append("0x" + Integer.toHexString(b) + " ");
    }
    return result.toString();
  }
}
