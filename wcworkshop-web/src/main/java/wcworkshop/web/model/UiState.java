package wcworkshop.web.model;

import java.util.List;

public class UiState {

  private List<String> openProjects;
  private List<String> openTabs;
  private String currentProject;
  private String currentTab;

  public List<String> getOpenProjects() {
    return openProjects;
  }

  public void setOpenProjects(List<String> openProjects) {
    this.openProjects = openProjects;
  }

  public List<String> getOpenTabs() {
    return openTabs;
  }

  public void setOpenTabs(List<String> openTabs) {
    this.openTabs = openTabs;
  }

  public String getCurrentProject() {
    return currentProject;
  }

  public void setCurrentProject(String currentProject) {
    this.currentProject = currentProject;
  }

  public String getCurrentTab() {
    return currentTab;
  }

  public void setCurrentTab(String currentTab) {
    this.currentTab = currentTab;
  }
}
