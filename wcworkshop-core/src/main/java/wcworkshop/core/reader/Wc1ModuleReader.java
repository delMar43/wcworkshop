package wcworkshop.core.reader;

import wcworkshop.core.binary.Wc1ModuleFile;

public class Wc1ModuleReader {
  private static final Wc1ModuleReader instance = new Wc1ModuleReader();

  public Wc1ModuleFile read(String extension) {
    return ReaderHelper.getInstance().read("MODULE." + extension, "wc1.module.mapping", Wc1ModuleFile.class);
  }

  private Wc1ModuleReader() {
  }

  public static Wc1ModuleReader getInstance() {
    return instance;
  }
}
