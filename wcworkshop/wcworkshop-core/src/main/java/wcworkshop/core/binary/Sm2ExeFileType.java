package wcworkshop.core.binary;

public enum Sm2ExeFileType {

  DOS("sm2.exe.dos", "SM2.EXE"), GOG("sm2.exe.gog", "SM2.EXE"), KS("sm2.exe.ks", "SM2.EXE");

  private String mapping;
  private String exeName;

  private Sm2ExeFileType(String mapping, String exeName) {
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
