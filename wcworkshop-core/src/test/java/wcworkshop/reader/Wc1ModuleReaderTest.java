package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.reader.Wc1ModuleReader;

public class Wc1ModuleReaderTest {

  // private Wc1ModuleReader reader = new Wc1ModuleReader();
  // private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  // private Wc1AiPilotRepo pilotRepo = Wc1AiPilotRepo.getInstance();

  @Test
  public void readFile() {
    Wc1ModuleReader.getInstance().read("000");

    System.out.println("done");

  }

}
