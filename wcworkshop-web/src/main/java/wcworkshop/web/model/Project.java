package wcworkshop.web.model;

public class Project {

  private String owner;
  private String title;
  private String description;
  private EngineType engineType;
  private CampaignModel campaignModel;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EngineType getEngineType() {
    return engineType;
  }

  public void setEngineType(EngineType engineType) {
    this.engineType = engineType;
  }

  public CampaignModel getCampaignModel() {
    return campaignModel;
  }

  public void setCampaignModel(CampaignModel campaignModel) {
    this.campaignModel = campaignModel;
  }

}
