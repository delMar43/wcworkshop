package wcworkshop.core.binary;

public class Wc1ModuleNavPointData {

  private String id;
  private short type;
  private byte[] xCoords;
  private byte unknown1;
  private byte[] yCoords;
  private byte unknown2;
  private byte[] zCoords;
  private byte unknown3;
  private byte unknown4;
  private Wc1ModuleNavPointManipulationData[] navPointManipulation;
  private short[] shipImagesToLoad;
  private short[] presentShips;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public short getType() {
    return type;
  }

  public void setType(short type) {
    this.type = type;
  }

  public byte[] getxCoords() {
    return xCoords;
  }

  public void setxCoords(byte[] xCoords) {
    this.xCoords = xCoords;
  }

  public byte[] getyCoords() {
    return yCoords;
  }

  public void setyCoords(byte[] yCoords) {
    this.yCoords = yCoords;
  }

  public byte[] getzCoords() {
    return zCoords;
  }

  public void setzCoords(byte[] zCoords) {
    this.zCoords = zCoords;
  }

  public Wc1ModuleNavPointManipulationData[] getNavPointManipulation() {
    return navPointManipulation;
  }

  public void setNavPointManipulation(Wc1ModuleNavPointManipulationData[] navPointManipulation) {
    this.navPointManipulation = navPointManipulation;
  }

  public short[] getShipImagesToLoad() {
    return shipImagesToLoad;
  }

  public void setShipImagesToLoad(short[] shipImagesToLoad) {
    this.shipImagesToLoad = shipImagesToLoad;
  }

  public short[] getPresentShips() {
    return presentShips;
  }

  public void setPresentShips(short[] presentShips) {
    this.presentShips = presentShips;
  }

  public byte getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(byte unknown1) {
    this.unknown1 = unknown1;
  }

  public byte getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(byte unknown2) {
    this.unknown2 = unknown2;
  }

  public byte getUnknown3() {
    return unknown3;
  }

  public void setUnknown3(byte unknown3) {
    this.unknown3 = unknown3;
  }

  public byte getUnknown4() {
    return unknown4;
  }

  public void setUnknown4(byte unknown4) {
    this.unknown4 = unknown4;
  }

}
