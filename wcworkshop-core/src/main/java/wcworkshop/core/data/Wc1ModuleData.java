package wcworkshop.core.data;

import java.util.List;

public class Wc1ModuleData {

  public static final Wc1ModuleData EMPTY = new Wc1ModuleData();

  private List<List<Short>> autopilotShips;
  private List<Wc1NavPoint> navPoints;
  private List<Wc1NavPointInfo> navPointInfo;
  private List<Wc1MissionShipData> missionShipData;
  private List<String> systemNames;
  private List<String> wingNames;

  public List<List<Short>> getAutopilotShips() {
    return autopilotShips;
  }

  public void setAutopilotShips(List<List<Short>> autopilotShips) {
    this.autopilotShips = autopilotShips;
  }

  public List<Wc1NavPointInfo> getNavPointInfo() {
    return navPointInfo;
  }

  public void setNavPointInfo(List<Wc1NavPointInfo> navPointInfo) {
    this.navPointInfo = navPointInfo;
  }

  public List<String> getSystemNames() {
    return systemNames;
  }

  public void setSystemNames(List<String> systemNames) {
    this.systemNames = systemNames;
  }

  public List<Wc1MissionShipData> getMissionShipData() {
    return missionShipData;
  }

  public void setMissionShipData(List<Wc1MissionShipData> missionShipData) {
    this.missionShipData = missionShipData;
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
