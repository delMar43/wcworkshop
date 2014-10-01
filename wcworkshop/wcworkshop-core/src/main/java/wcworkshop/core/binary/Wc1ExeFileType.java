package wcworkshop.core.binary;

public enum Wc1ExeFileType {

  DOS("wc1.exe.dos", "WC.EXE"), GOG("wc1.exe.gog", "WC.EXE"), KS("wc1.exe.ks", "WC1.EXE");

  private String mapping;
  private String exeName;

  private Wc1ExeFileType(String mapping, String exeName) {
    this.mapping = mapping;
    this.exeName = exeName;
  }

  public String getMapping() {
    return mapping;
  }

  public String getExeName() {
    return exeName;
  }
}
