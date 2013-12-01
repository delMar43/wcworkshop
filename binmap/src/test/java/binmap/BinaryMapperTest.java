package binmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    Wc1Savegame savegame = bm.toJava(data, mapping, Wc1Savegame.class);
    System.out.println("done " + savegame);
  }

  public byte[] readFile(InputStream inputStream) throws FileNotFoundException, IOException {
    byte[] buffer;
    buffer = IOUtils.toByteArray(inputStream);
    return buffer;
  }
}
