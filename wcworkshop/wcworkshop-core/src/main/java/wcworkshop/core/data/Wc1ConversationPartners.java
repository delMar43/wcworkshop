package wcworkshop.core.data;

public class Wc1ConversationPartners {

  private byte leftSeat;
  private byte rightSeat;

  public Wc1ConversationPartners() {
  }

  public Wc1ConversationPartners(byte leftSeat, byte rightSeat) {
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
