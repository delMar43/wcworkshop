package wcworkshop.core.data;

public class Wc1Medal {
  private byte value;
  private String name;

  public Wc1Medal() {
  }

  public Wc1Medal(byte value, String name) {
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
