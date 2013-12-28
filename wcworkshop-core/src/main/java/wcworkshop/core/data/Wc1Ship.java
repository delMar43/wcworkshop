package wcworkshop.core.data;

public class Wc1Ship {
  public static final Wc1Ship EMPTY = new Wc1Ship();

  private byte value;
  private String name;
  private boolean flyable;

  public Wc1Ship() {
  }

  public Wc1Ship(byte value, String name, boolean flyable) {
    this.value = value;
    this.name = name;
    this.flyable = flyable;
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

  public void setFlyable(boolean flyable) {
    this.flyable = flyable;
  }

  public boolean isFlyable() {
    return flyable;
  }
}
