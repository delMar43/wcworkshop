package wcworkshop.core.data;

import java.util.List;

public class Wc1NavPoint {

  private String id;
  private boolean visible;
  private short unknown1;
  private short xPos;
  private short unknown2;
  private short yPos;
  private short unknown3;
  private byte inSystemJumpPoint;
  private byte isJumpPoint;
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
    String result = " " + id + (visible ? " " : " (invisible) ") + ", u1: " + hex(unknown1) + ", xPos: " + xPos + ", u2: " + hex(unknown2) + ", yPos: " + yPos
        + ", unknown3: " + hex(unknown3) + ", unknown4: " + hex(inSystemJumpPoint) + ", unknown5: " + hex(isJumpPoint);

    boolean hasShipsToLoad = shipsToLoad != null && shipsToLoad.size() > 0;
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

  private String hex(int value) {
    return "0x" + Integer.toHexString(value).toUpperCase();
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

  public short getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(short unknown1) {
    this.unknown1 = unknown1;
  }

  public short getxPos() {
    return xPos;
  }

  public void setxPos(short xPos) {
    this.xPos = xPos;
  }

  public short getyPos() {
    return yPos;
  }

  public void setyPos(short yPos) {
    this.yPos = yPos;
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

  public short getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(short unknown2) {
    this.unknown2 = unknown2;
  }

  public byte getInSystemJumpPoint() {
    return inSystemJumpPoint;
  }

  public void setInSystemJumpPoint(byte inSystemJumpPoint) {
    this.inSystemJumpPoint = inSystemJumpPoint;
  }

  public byte getIsJumpPoint() {
    return isJumpPoint;
  }

  public void setIsJumpPoint(byte isJumpPoint) {
    this.isJumpPoint = isJumpPoint;
  }

  public short getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(short unknown3) {
    this.unknown3 = unknown3;
  }
}
