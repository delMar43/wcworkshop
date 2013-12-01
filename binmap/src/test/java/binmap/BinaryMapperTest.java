package binmap;

import org.junit.Assert;
import org.junit.Test;

public class BinaryMapperTest {
  private MappingFactory binaryMapper = new MappingFactory();

  @Test
  public void test() {
    Mapping mapping = binaryMapper.readMapping("savegame.mapping");

    Assert.assertEquals("test.Wc1Savegame", mapping.getClassName());
    Assert.assertEquals(828, mapping.getSize());
  }
}
