package wcworkshop.core.writer;

public class Wc1BriefingWriter {
  private static final Wc1BriefingWriter instance = new Wc1BriefingWriter();

  private WriterHelper writerHelper = WriterHelper.getInstance();

  public void write(String outputPath, Object source) {
    writerHelper.writeDynamic(outputPath, "wc1.briefing.mapping", source);
  }

  private Wc1BriefingWriter() {
  }

  public static Wc1BriefingWriter getInstance() {
    return instance;
  }
}
