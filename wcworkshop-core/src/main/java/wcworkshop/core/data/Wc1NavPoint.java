package wcworkshop.core.data;

import java.util.List;

public class Wc1NavPoint {

  private byte type;
  private String id;
  private boolean visible;
  private int xPos;
  private short unknown1;
  private int yPos;
  private short unknown2;
  private int zPos;
  private short unknown3;
  private List<Short> shipImagesToLoad;
  private List<Wc1NavPointManipulation> navPointManipulations;
  private List<Short> presentShips;

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
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

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public List<Short> getShipImagesToLoad() {
    return shipImagesToLoad;
  }

  public void setShipImagesToLoad(List<Short> shipImagesToLoad) {
    this.shipImagesToLoad = shipImagesToLoad;
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

  public short getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(short unknown3) {
    this.unknown3 = unknown3;
  }

  public int getzPos() {
    return zPos;
  }

  public void setzPos(int zPos) {
    this.zPos = zPos;
  }

  public List<Short> getPresentShips() {
    return presentShips;
  }

  public void setPresentShips(List<Short> presentShips) {
    this.presentShips = presentShips;
  }
}
