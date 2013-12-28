package wcworkshop.core.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Wc1NavPoint {
  private short type;
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
  private short iconValue;
  private short shipOrPoint;
  private String text;

  public short getType() {
    return type;
  }

  public void setType(short type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = StringUtils.trim(id);
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public short getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(short unknown1) {
    this.unknown1 = unknown1;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public short getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(short unknown2) {
    this.unknown2 = unknown2;
  }

  public int getzPos() {
    return zPos;
  }

  public void setzPos(int zPos) {
    this.zPos = zPos;
  }

  public short getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(short unknown3) {
    this.unknown3 = unknown3;
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

  public List<Short> getPresentShips() {
    return presentShips;
  }

  public void setPresentShips(List<Short> presentShips) {
    this.presentShips = presentShips;
  }

  public short getIconValue() {
    return iconValue;
  }

  public void setIconValue(short iconValue) {
    this.iconValue = iconValue;
  }

  public short getShipOrPoint() {
    return shipOrPoint;
  }

  public void setShipOrPoint(short shipOrPoint) {
    this.shipOrPoint = shipOrPoint;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
