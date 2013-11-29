package wcworkshop.core.data;

public class Wc1Ship {

  private byte value;
  private String name;

  public Wc1Ship() {
  }

  public Wc1Ship(byte value, String name) {
    this.value = value;
    this.name = name;
  }

  public byte getValue() {
    return value;
  }

  public void setValue(byte value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
