package binmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import test.Wc1Savegame;

public class BinaryMapperTest {
  private MappingFactory mappingFactory = new MappingFactory();

  @Test
  public void test() throws FileNotFoundException, IOException {
    Mapping mapping = mappingFactory.createMapper("savegame.mapping");

    Assert.assertEquals("test.Wc1Savegame", mapping.getClassName());
    Assert.assertEquals(828, mapping.getSize());

    byte[] data = readFile(this.getClass().getClassLoader().getResourceAsStream("SAVEGAME.WLD"));

    BinaryMapper bm = new BinaryMapper();
    List<Wc1Savegame> savegames = new ArrayList<>();
    for (int idx = 0; idx < 8; ++idx) {
      int offset = idx * mapping.getSize();
      Wc1Savegame savegame = bm.toJava(data, mapping, offset, Wc1Savegame.class);
      savegames.add(savegame);
    }
    System.out.println("done " + savegames);
  }

  public byte[] readFile(InputStream inputStream) throws FileNotFoundException, IOException {
    byte[] buffer;
    buffer = IOUtils.toByteArray(inputStream);
    return buffer;
  }
}
