package wcworkshop.core.data;

public class Wc1AiPilot {

  private byte value;
  private String name;

  public Wc1AiPilot(byte value, String name) {
    this.value = value;
    this.name = name;
  }

  public byte getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
