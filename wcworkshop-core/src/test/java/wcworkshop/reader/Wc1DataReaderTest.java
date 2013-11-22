package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.reader.Wc1GameDataReader;

public class Wc1DataReaderTest {

  private Wc1GameDataReader reader = Wc1GameDataReader.getInstance();

  @Test
  public void fill() {
    Wc1GameData data = reader.readData();
    System.out.println("done");
  }
}
