package wcworkshop.core.reader;

import wcworkshop.core.binary.Wc1CampFile;

public class Wc1CampReader {
  private static final Wc1CampReader instance = new Wc1CampReader();

  public Wc1CampFile read(String extension) {
    return ReaderHelper.getInstance().read("CAMP." + extension, "wc1.camp.mapping", Wc1CampFile.class);
  }

  private Wc1CampReader() {
  }

  public static Wc1CampReader getInstance() {
    return instance;
  }
}
