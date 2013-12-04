package binmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import test.Wc1Savegame;

public class BinaryReaderTest {
  private MappingFactory mappingFactory = MappingFactory.getInstance();

  @Test
  public void test() throws FileNotFoundException, IOException {
    List<Wc1Savegame> savegames = loadSavegame("savegame.mapping");
    System.out.println("done " + savegames);
  }

  public List<Wc1Savegame> loadSavegame(String mapperName) throws FileNotFoundException, IOException {
    Mapping mapping = mappingFactory.createMapper("savegame.mapping");

    byte[] data = readFile(this.getClass().getClassLoader().getResourceAsStream("SAVEGAME.WLD"));

    BinaryReader bm = BinaryReader.getInstance();
    List<Wc1Savegame> savegames = new ArrayList<>();
    for (int idx = 0; idx < 8; ++idx) {
      int offset = idx * mapping.getSize();
      Wc1Savegame savegame = bm.toJava(data, mapping, offset, Wc1Savegame.class);
      savegames.add(savegame);
    }
    return savegames;
  }

  public byte[] readFile(InputStream inputStream) throws FileNotFoundException, IOException {
    byte[] buffer;
    buffer = IOUtils.toByteArray(inputStream);
    return buffer;
  }
}
