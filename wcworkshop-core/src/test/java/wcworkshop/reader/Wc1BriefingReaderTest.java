package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.reader.Wc1BriefingReader;

public class Wc1BriefingReaderTest {

  @Test
  public void readFile() {
    Wc1BriefingReader.getInstance().read("000");

    System.out.println("done");
  }
}
