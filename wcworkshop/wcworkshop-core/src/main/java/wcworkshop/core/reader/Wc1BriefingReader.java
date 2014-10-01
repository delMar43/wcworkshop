package wcworkshop.core.reader;

import wcworkshop.core.binary.Wc1BriefingFile;

public class Wc1BriefingReader {
  private static final Wc1BriefingReader instance = new Wc1BriefingReader();

  public Wc1BriefingFile read(String extension) {
    return ReaderHelper.getInstance().read("BRIEFING." + extension, "wc1.briefing.mapping", Wc1BriefingFile.class);
  }

  private Wc1BriefingReader() {
  }

  public static Wc1BriefingReader getInstance() {
    return instance;
  }
}
