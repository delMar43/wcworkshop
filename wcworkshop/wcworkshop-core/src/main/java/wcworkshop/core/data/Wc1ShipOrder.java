package wcworkshop.core.data;

public class Wc1ShipOrder {
  private byte value;
  private String text;

  public Wc1ShipOrder(byte value, String text) {
    this.value = value;
    this.text = text;
  }

  public byte getValue() {
    return value;
  }

  public String getText() {
    return text;
  }
}
