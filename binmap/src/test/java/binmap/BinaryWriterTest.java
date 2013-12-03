package binmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import test.Wc1Savegame;

public class BinaryWriterTest {

  private BinaryReaderTest readerTest = new BinaryReaderTest();
  private MappingFactory mappingFactory = new MappingFactory();

  @Test
  public void test() throws FileNotFoundException, IOException {
    Mapping mapping = mappingFactory.createMapper("savegame.mapping");

    List<Wc1Savegame> savegames = readerTest.loadSavegame("savegame.mapping");

    BinaryWriter bw = new BinaryWriter();
    // for (int idx = 0; idx < 8; ++idx) {
    Wc1Savegame savegame = savegames.get(0);
    byte[] binary = bw.toBinary(savegame, mapping);
    // }
  }
}
