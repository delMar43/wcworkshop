package wcworkshop.core.binary;

public class Wc1CampBarData {
  public static final Wc1CampBarData EMPTY = new Wc1CampBarData((byte) 0, (byte) 0);

  private byte leftSeat;
  private byte rightSeat;

  public Wc1CampBarData() {
  }

  public Wc1CampBarData(byte leftSeat, byte rightSeat) {
    this.leftSeat = leftSeat;
    this.rightSeat = rightSeat;
  }

  public byte getLeftSeat() {
    return leftSeat;
  }

  public void setLeftSeat(byte leftSeat) {
    this.leftSeat = leftSeat;
  }

  public byte getRightSeat() {
    return rightSeat;
  }

  public void setRightSeat(byte rightSeat) {
    this.rightSeat = rightSeat;
  }

}
