package binmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import test.Wc1Savegame;

public class BinaryWriterTest {

  private BinaryReaderTest readerTest = new BinaryReaderTest();
  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private BinaryUtils binaryUtils = BinaryUtils.getInstance();

  @Test
  public void test() throws FileNotFoundException, IOException {
    Mapping mapping = mappingFactory.createMapping("savegame.mapping");

    List<Wc1Savegame> savegames = readerTest.loadSavegame("savegame.mapping");

    byte[] result = new byte[mapping.getSize() * 8];

    BinaryWriter bw = BinaryWriter.getInstance();
    for (int idx = 0; idx < 8; ++idx) {
      Wc1Savegame savegame = savegames.get(idx);
      byte[] binary = bw.toBinary(savegame, mapping);
      binaryUtils.copyIntoArray(binary, result, mapping.getSize() * idx);
    }

    FileOutputStream fos = new FileOutputStream("d:\\savegame.wld");
    fos.write(result);
    fos.flush();
    fos.close();
  }
}
