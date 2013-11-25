package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.reader.WcaVxxReader;

public class WcaVxxReaderTest {

  private WcaVxxReader reader = WcaVxxReader.getInstance();

  @Test
  public void read() {
    reader.read("C:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/ship.v04");
  }
}
