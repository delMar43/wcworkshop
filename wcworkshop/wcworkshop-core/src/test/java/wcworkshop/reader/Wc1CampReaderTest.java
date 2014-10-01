package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.reader.Wc1CampReader;

public class Wc1CampReaderTest {

  @Test
  public void readFile() {
    Wc1CampReader.getInstance().read("000");

    System.out.println("done");
  }
}
