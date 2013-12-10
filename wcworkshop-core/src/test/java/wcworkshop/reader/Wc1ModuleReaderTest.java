package wcworkshop.reader;

import org.junit.Test;

import wcworkshop.core.binary.Wc1ModuleFile;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1ModuleReaderTest {

  // private Wc1ModuleReader reader = new Wc1ModuleReader();
  // private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  // private Wc1AiPilotRepo pilotRepo = Wc1AiPilotRepo.getInstance();

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @Test
  public void readFile() {
    // String path = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/MODULE.000";
    // Wc1ModuleData moduleData = reader.readModuleFile(path);
    //
    // for (Wc1MissionShipData data : moduleData.getMissionShipData()) {
    // System.out.println(shipRepo.getShip(data.getType()).getName() + ", " + pilotRepo.getAiPilot(data.getAiPilot()).getName());
    // }

    Mapping mapper = mappingFactory.createMapping("wc1.module.mapping");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "MODULE.000");
    Wc1ModuleFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1ModuleFile.class);

    System.out.println("done");

  }

}
