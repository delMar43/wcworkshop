package wcworkshop.web.model;

public enum EngineType {

  WC1(13, 4, 8, 22);

  private int nrOfSeries;
  private int nrOfMissionsPerSeries;
  private int nrOfNPCs;
  private int nrOfShips;

  private EngineType(int nrOfSeries, int nrOfMissionsPerSeries, int nrOfNPCs, int nrOfShips) {
    this.nrOfSeries = nrOfSeries;
    this.nrOfMissionsPerSeries = nrOfMissionsPerSeries;
    this.nrOfNPCs = nrOfNPCs;
    this.nrOfShips = nrOfShips;
  }

  public int getNrOfSeries() {
    return nrOfSeries;
  }

  public int getNrOfMissionsPerSeries() {
    return nrOfMissionsPerSeries;
  }

  public int getNrOfNPCs() {
    return nrOfNPCs;
  }

  public int getNrOfShips() {
    return nrOfShips;
  }
}
