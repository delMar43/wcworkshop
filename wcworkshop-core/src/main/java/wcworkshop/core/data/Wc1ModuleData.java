package wcworkshop.core.data;

import java.util.List;

public class Wc1ModuleData {

  public static final Wc1ModuleData EMPTY = new Wc1ModuleData();

  private List<String> systemNames;
  private List<String> wingNames;
  private List<Wc1NavPoint> navPoints;

  public List<String> getSystemNames() {
    return systemNames;
  }

  public void setSystemNames(List<String> systemNames) {
    this.systemNames = systemNames;
  }

  public List<String> getWingNames() {
    return wingNames;
  }

  public void setWingNames(List<String> wingNames) {
    this.wingNames = wingNames;
  }

  public List<Wc1NavPoint> getNavPoints() {
    return navPoints;
  }

  public void setNavPoints(List<Wc1NavPoint> navPoints) {
    this.navPoints = navPoints;
  }
}
