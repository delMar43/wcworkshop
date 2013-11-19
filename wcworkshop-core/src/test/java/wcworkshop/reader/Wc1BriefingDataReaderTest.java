package wcworkshop.reader;

import java.util.Arrays;

import org.junit.Test;

import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.reader.Wc1BriefingReader;

public class Wc1BriefingDataReaderTest {

  private Wc1BriefingReader reader = new Wc1BriefingReader();

  @Test
  public void readFile() {
    String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/BRIEFING.002";
    Wc1BriefingData briefingData = reader.readBriefingFile(path);
    System.out.println("Filesize: " + briefingData.getFilesize());
    Object[] array = briefingData.getBlockOffsets().toArray();
    System.out.println(array.length + " Block offsets: " + Arrays.toString(array));
  }
}
