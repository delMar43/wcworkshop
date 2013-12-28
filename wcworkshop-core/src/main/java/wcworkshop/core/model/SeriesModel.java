package wcworkshop.core.model;

public class SeriesModel {

  private int seriesNr;
  private String systemName;

  public SeriesModel() {
  }

  public SeriesModel(int seriesNr, String systemName) {
    this.seriesNr = seriesNr;
    this.systemName = systemName;
  }

  public String getLabel() {
    return seriesNr + ": " + systemName;
  }

  public int getSeriesNr() {
    return seriesNr;
  }

  public void setSeriesNr(int seriesNr) {
    this.seriesNr = seriesNr;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

}
