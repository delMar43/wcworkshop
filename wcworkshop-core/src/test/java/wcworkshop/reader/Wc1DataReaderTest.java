package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.data.Wc1Data;
import wcworkshop.core.reader.Wc1DataReader;

public class Wc1DataReaderTest {

  private Wc1DataReader reader = Wc1DataReader.getInstance();

  @Test
  public void fill() {
    Wc1Data data = reader.readData();
    System.out.println("done");
  }
}
