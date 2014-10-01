package wcworkshop.core.dto;

public enum EngineType {

  WC1(13, 4, 8, 22, new String[] { "wc1.exe.dos", "wc1.exe.gog", "wc1.exe.ks", "sm2.exe.gog" });

  private int nrOfSeries;
  private int nrOfMissionsPerSeries;
  private int nrOfNPCs;
  private int nrOfShips;
  private String[] exeMappings;

  private EngineType(int nrOfSeries, int nrOfMissionsPerSeries, int nrOfNPCs, int nrOfShips, String[] exeMappings) {
    this.nrOfSeries = nrOfSeries;
    this.nrOfMissionsPerSeries = nrOfMissionsPerSeries;
    this.nrOfNPCs = nrOfNPCs;
    this.nrOfShips = nrOfShips;
    this.exeMappings = exeMappings;
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

  public String[] getExeMappings() {
    return exeMappings;
  }
}
