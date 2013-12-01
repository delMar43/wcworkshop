package binmap;

import org.junit.Assert;
import org.junit.Test;

public class BinaryMapperTest {
  private MappingFactory mappingFactory = new MappingFactory();

  @Test
  public void test() {
    Mapping mapping = mappingFactory.createMapper("savegame.mapping");

    Assert.assertEquals("test.Wc1Savegame", mapping.getClassName());
    Assert.assertEquals(828, mapping.getSize());
  }
}
