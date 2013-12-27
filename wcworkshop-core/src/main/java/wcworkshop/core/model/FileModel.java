package wcworkshop.core.model;

public class FileModel {

  private String filename;
  private String changeDate;

  public FileModel() {
  }

  public FileModel(String filename, String changeDate) {
    this.filename = filename;
    this.changeDate = changeDate;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(String changeDate) {
    this.changeDate = changeDate;
  }

}
