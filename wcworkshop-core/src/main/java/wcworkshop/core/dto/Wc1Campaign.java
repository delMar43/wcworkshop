package wcworkshop.core.dto;

import java.util.List;

public class Wc1Campaign implements Campaign {

  private List<Wc1Series> series;
  private List<Wc1Pilot> pilots;
  private List<Wc1Ship> ships;
  private Wc1Funeral funeral;
  private Wc1MedalCeremony medalCeremony;

  public List<Wc1Series> getSeries() {
    return series;
  }

  public void setSeries(List<Wc1Series> series) {
    this.series = series;
  }

  public List<Wc1Pilot> getPilots() {
    return pilots;
  }

  public void setPilots(List<Wc1Pilot> pilots) {
    this.pilots = pilots;
  }

  public List<Wc1Ship> getShips() {
    return ships;
  }

  public void setShips(List<Wc1Ship> ships) {
    this.ships = ships;
  }

  public Wc1Funeral getFuneral() {
    return funeral;
  }

  public void setFuneral(Wc1Funeral funeral) {
    this.funeral = funeral;
  }

  public Wc1MedalCeremony getMedalCeremony() {
    return medalCeremony;
  }

  public void setMedalCeremony(Wc1MedalCeremony medalCeremony) {
    this.medalCeremony = medalCeremony;
  }

}
