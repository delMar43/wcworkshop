package wcworkshop.core.model;

import java.util.List;

public class ProjectDownloadModel {

  private String projectId;
  private String projectTitle;
  private List<FileModel> files;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getProjectTitle() {
    return projectTitle;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public List<FileModel> getFiles() {
    return files;
  }

  public void setFiles(List<FileModel> files) {
    this.files = files;
  }

}
