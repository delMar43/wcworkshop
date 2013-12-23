package wcworkshop.core.binary;

public class Wc1GameFiles {

  private Wc1CampFile campFile;
  private Wc1BriefingFile briefingFile;
  private Wc1ModuleFile moduleFile;

  public Wc1GameFiles() {
  }

  public Wc1GameFiles(Wc1ModuleFile moduleFile, Wc1CampFile campFile, Wc1BriefingFile briefingFile) {
    this.moduleFile = moduleFile;
    this.campFile = campFile;
    this.briefingFile = briefingFile;
  }

  public Wc1CampFile getCampFile() {
    return campFile;
  }

  public void setCampFile(Wc1CampFile campFile) {
    this.campFile = campFile;
  }

  public Wc1BriefingFile getBriefingFile() {
    return briefingFile;
  }

  public void setBriefingFile(Wc1BriefingFile briefingFile) {
    this.briefingFile = briefingFile;
  }

  public Wc1ModuleFile getModuleFile() {
    return moduleFile;
  }

  public void setModuleFile(Wc1ModuleFile moduleFile) {
    this.moduleFile = moduleFile;
  }

}
