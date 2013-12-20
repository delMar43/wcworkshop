package wcworkshop.core.dto;

import java.util.Map;

public class Project {

  private String owner;
  private String title;
  private Map<String, String> descriptions;
  private String website;
  private EngineType engineType;
  private Wc1Campaign campaign;

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Map<String, String> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(Map<String, String> descriptions) {
    this.descriptions = descriptions;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public EngineType getEngineType() {
    return engineType;
  }

  public void setEngineType(EngineType engineType) {
    this.engineType = engineType;
  }

  public Wc1Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Wc1Campaign campaign) {
    this.campaign = campaign;
  }
}
