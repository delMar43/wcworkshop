package wcworkshop.core.data;

public class Wc1MissionShipData {

  private short type;
  private byte iff;
  private byte orders;
  private int xCoord;
  private int yCoord;
  private int zCoord;
  private byte speed;
  private byte size;
  private byte aiPilot;
  private byte secondaryTarget;
  private byte formation;
  private byte primaryTarget;

  public short getType() {
    return type;
  }

  public void setType(short type) {
    this.type = type;
  }

  public byte getIff() {
    return iff;
  }

  public void setIff(byte iff) {
    this.iff = iff;
  }

  public byte getOrders() {
    return orders;
  }

  public void setOrders(byte orders) {
    this.orders = orders;
  }

  public int getxCoord() {
    return xCoord;
  }

  public void setxCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public int getyCoord() {
    return yCoord;
  }

  public void setyCoord(int yCoord) {
    this.yCoord = yCoord;
  }

  public int getzCoord() {
    return zCoord;
  }

  public void setzCoord(int zCoord) {
    this.zCoord = zCoord;
  }

  public byte getSpeed() {
    return speed;
  }

  public void setSpeed(byte speed) {
    this.speed = speed;
  }

  public byte getSize() {
    return size;
  }

  public void setSize(byte size) {
    this.size = size;
  }

  public byte getAiPilot() {
    return aiPilot;
  }

  public void setAiPilot(byte aiPilot) {
    this.aiPilot = aiPilot;
  }

  public byte getSecondaryTarget() {
    return secondaryTarget;
  }

  public void setSecondaryTarget(byte secondaryTarget) {
    this.secondaryTarget = secondaryTarget;
  }

  public byte getFormation() {
    return formation;
  }

  public void setFormation(byte formation) {
    this.formation = formation;
  }

  public byte getPrimaryTarget() {
    return primaryTarget;
  }

  public void setPrimaryTarget(byte primaryTarget) {
    this.primaryTarget = primaryTarget;
  }

}
