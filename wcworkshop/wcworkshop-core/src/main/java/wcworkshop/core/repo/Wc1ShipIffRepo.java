package wcworkshop.core.repo;

public class Wc1ShipIffRepo {
  private static final Wc1ShipIffRepo instance = new Wc1ShipIffRepo();

  public String getIff(byte value) {
    if (value == (byte) 0) {
      return "Confed";
    } else if (value == (byte) 1) {
      return "Kilrathi";
    } else if (value == (byte) 2) {
      return "Neutral";
    }
    throw new RuntimeException("Unknown IFF: " + value);
  }

  public static Wc1ShipIffRepo getInstance() {
    return instance;
  }
}
