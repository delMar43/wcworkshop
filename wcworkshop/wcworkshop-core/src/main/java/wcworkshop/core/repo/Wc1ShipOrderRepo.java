package wcworkshop.core.repo;

import java.util.HashMap;
import java.util.Map;

import wcworkshop.core.data.Wc1ShipOrder;

public class Wc1ShipOrderRepo {
  private static final Wc1ShipOrderRepo instance = new Wc1ShipOrderRepo();
  private Map<Byte, Wc1ShipOrder> orders = new HashMap<>();

  private Wc1ShipOrderRepo() {
    orders.put((byte) 0, new Wc1ShipOrder((byte) 0, "Attack0"));
    orders.put((byte) 2, new Wc1ShipOrder((byte) 2, "Attack2"));
    orders.put((byte) 4, new Wc1ShipOrder((byte) 4, "Follow"));
    orders.put((byte) 6, new Wc1ShipOrder((byte) 6, "Jump out"));
    orders.put((byte) 7, new Wc1ShipOrder((byte) 7, "Jump in"));
    orders.put((byte) -1, new Wc1ShipOrder((byte) -1, "Inactive"));
  }

  public Wc1ShipOrder getOrder(byte value) {
    return orders.get(value);
  }

  public static Wc1ShipOrderRepo getInstance() {
    return instance;
  }
}
