package wcworkshop.core.repo;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1Ship;

public class Wc1ShipRepo {
  private static final Wc1ShipRepo instance = new Wc1ShipRepo();

  private List<Wc1Ship> ships = new ArrayList<>();

  private Wc1ShipRepo() {
    ships.add(new Wc1Ship((byte) 0, "Hornet"));
    ships.add(new Wc1Ship((byte) 1, "Rapier"));
    ships.add(new Wc1Ship((byte) 2, "Scimitar"));
    ships.add(new Wc1Ship((byte) 3, "Raptor"));
    ships.add(new Wc1Ship((byte) 4, "Venture"));
    ships.add(new Wc1Ship((byte) 5, "Dilligent"));
    ships.add(new Wc1Ship((byte) 6, "Drayman"));
    ships.add(new Wc1Ship((byte) 7, "Exeter"));
    ships.add(new Wc1Ship((byte) 8, "Tiger's Claw"));
    ships.add(new Wc1Ship((byte) 9, "Salthi"));
    ships.add(new Wc1Ship((byte) 10, "Dralthi"));
    ships.add(new Wc1Ship((byte) 11, "Krant"));
    ships.add(new Wc1Ship((byte) 12, "Gratha"));
    ships.add(new Wc1Ship((byte) 13, "Jalthi"));
    ships.add(new Wc1Ship((byte) 14, "Hhriss"));
    ships.add(new Wc1Ship((byte) 15, "Dorkir"));
    ships.add(new Wc1Ship((byte) 16, "Lumbari"));
    ships.add(new Wc1Ship((byte) 17, "Ralari"));
    ships.add(new Wc1Ship((byte) 18, "Fralthi"));
    ships.add(new Wc1Ship((byte) 19, "Snakeir"));
    ships.add(new Wc1Ship((byte) 20, "Sivar"));
    ships.add(new Wc1Ship((byte) 21, "Starpost"));
    ships.add(new Wc1Ship((byte) 22, "Asteroids"));
    ships.add(new Wc1Ship((byte) 23, "Mines"));
  }

  public Wc1Ship getShip(short value) {
    if (value >= ships.size()) {
      throw new RuntimeException("Unknown ship: " + value);
    }

    if (value == -1) {
      return Wc1Ship.EMPTY;
    }

    return ships.get(value);
  }

  public static Wc1ShipRepo getInstance() {
    return instance;
  }
}
