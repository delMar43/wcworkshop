package wcworkshop.web.model;

import java.util.List;

public class UiState {

  private String username;
  private List<String> openCampaigns;
  private List<String> openTabs;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getOpenCampaigns() {
    return openCampaigns;
  }

  public void setOpenCampaigns(List<String> openCampaigns) {
    this.openCampaigns = openCampaigns;
  }

  public List<String> getOpenTabs() {
    return openTabs;
  }

  public void setOpenTabs(List<String> openTabs) {
    this.openTabs = openTabs;
  }

}
