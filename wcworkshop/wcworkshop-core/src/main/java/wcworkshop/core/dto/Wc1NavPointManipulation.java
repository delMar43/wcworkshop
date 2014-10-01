package wcworkshop.core.dto;

public class Wc1NavPointManipulation {

  private byte navPoint;
  private boolean enableNavPoint;

  @Override
  public String toString() {
    return (enableNavPoint ? "show" : "hide") + " nav " + navPoint;
  }

  public byte getNavPoint() {
    return navPoint;
  }

  public void setNavPoint(byte navPoint) {
    this.navPoint = navPoint;
  }

  public boolean isEnableNavPoint() {
    return enableNavPoint;
  }

  public void setEnableNavPoint(boolean enableNavPoint) {
    this.enableNavPoint = enableNavPoint;
  }

}
