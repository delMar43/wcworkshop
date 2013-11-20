package wcworkshop.core.data;

import java.util.List;

public class Wc1NavPoint {

  private String id;
  private boolean visible;
  private List<Short> shipsToLoad;
  private List<Wc1NavPointManipulation> navPointManipulations;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    boolean hasNavPointManipulations = navPointManipulations != null && navPointManipulations.size() > 0;
    if (hasNavPointManipulations) {
      for (Wc1NavPointManipulation npm : navPointManipulations) {
        if (npm == null) {
          break;
        }
        sb.append(npm.toString());
      }
    }
    boolean hasShipsToLoad = shipsToLoad != null && shipsToLoad.size() > 0;
    String result = " " + id + (visible ? " " : " (invisible)");
    if (hasNavPointManipulations) {
      result += "\r\n navPoint manipulations: " + sb.toString();
    }
    if (hasShipsToLoad) {
      result += "\r\n ships to load:";
      for (Short ship : shipsToLoad) {
        result += " " + Wc1ModuleShip.getByValue(ship);
      }
    }
    return result;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public List<Short> getShipsToLoad() {
    return shipsToLoad;
  }

  public void setShipsToLoad(List<Short> shipsToLoad) {
    this.shipsToLoad = shipsToLoad;
  }

  public List<Wc1NavPointManipulation> getNavPointManipulations() {
    return navPointManipulations;
  }

  public void setNavPointManipulations(List<Wc1NavPointManipulation> navPointManipulations) {
    this.navPointManipulations = navPointManipulations;
  }
}
