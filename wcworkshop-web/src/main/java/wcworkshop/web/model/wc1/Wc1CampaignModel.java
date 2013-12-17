package wcworkshop.web.model.wc1;

import java.util.List;

import wcworkshop.web.model.CampaignModel;

public class Wc1CampaignModel implements CampaignModel {

  private List<Wc1SeriesModel> series;
  private List<Wc1PilotModel> pilots;
  private List<Wc1ShipModel> ships;
  private Wc1FuneralModel funeral;
  private Wc1MedalCeremonyModel medalCeremony;

  public List<Wc1SeriesModel> getSeries() {
    return series;
  }

  public void setSeries(List<Wc1SeriesModel> series) {
    this.series = series;
  }

  public List<Wc1PilotModel> getPilots() {
    return pilots;
  }

  public void setPilots(List<Wc1PilotModel> pilots) {
    this.pilots = pilots;
  }

  public List<Wc1ShipModel> getShips() {
    return ships;
  }

  public void setShips(List<Wc1ShipModel> ships) {
    this.ships = ships;
  }

  public Wc1FuneralModel getFuneral() {
    return funeral;
  }

  public void setFuneral(Wc1FuneralModel funeral) {
    this.funeral = funeral;
  }

  public Wc1MedalCeremonyModel getMedalCeremony() {
    return medalCeremony;
  }

  public void setMedalCeremony(Wc1MedalCeremonyModel medalCeremony) {
    this.medalCeremony = medalCeremony;
  }

}
