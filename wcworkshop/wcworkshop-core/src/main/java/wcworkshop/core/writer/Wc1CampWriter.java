package wcworkshop.core.writer;

public class Wc1CampWriter {
  private static final Wc1CampWriter instance = new Wc1CampWriter();

  private WriterHelper writerHelper = WriterHelper.getInstance();

  public void write(String outputPath, Object source) {
    writerHelper.write(outputPath, "wc1.camp.mapping", source);
  }

  private Wc1CampWriter() {
  }

  public static Wc1CampWriter getInstance() {
    return instance;
  }
}
