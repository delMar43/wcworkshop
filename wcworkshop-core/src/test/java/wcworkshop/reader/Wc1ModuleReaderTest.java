package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.data.Wc1ModuleData;
import wcworkshop.core.data.Wc1MissionShipData;
import wcworkshop.core.reader.Wc1ModuleReader;
import wcworkshop.core.repo.Wc1AiPilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;

public class Wc1ModuleReaderTest {

  private Wc1ModuleReader reader = new Wc1ModuleReader();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  private Wc1AiPilotRepo pilotRepo = Wc1AiPilotRepo.getInstance();

  @Test
  public void readFile() {
    String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/MODULE.000";
    Wc1ModuleData moduleData = reader.readModuleFile(path);

    for (Wc1MissionShipData data : moduleData.getMissionShipData()) {
      System.out.println(shipRepo.getShip(data.getType()).getName() + ", " + pilotRepo.getAiPilot(data.getAiPilot()).getName());
    }

  }

}
