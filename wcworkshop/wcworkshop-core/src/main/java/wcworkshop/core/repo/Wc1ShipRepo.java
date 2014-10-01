package wcworkshop.core.repo;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1Ship;

public class Wc1ShipRepo {
  private static final Wc1ShipRepo instance = new Wc1ShipRepo();

  private List<Wc1Ship> ships = new ArrayList<>();

  private Wc1ShipRepo() {
    ships.add(new Wc1Ship((byte) -1, "None", true));
    ships.add(new Wc1Ship((byte) 0, "Hornet", true));
    ships.add(new Wc1Ship((byte) 1, "Rapier", true));
    ships.add(new Wc1Ship((byte) 2, "Scimitar", true));
    ships.add(new Wc1Ship((byte) 3, "Raptor", true));
    ships.add(new Wc1Ship((byte) 4, "Venture", false));
    ships.add(new Wc1Ship((byte) 5, "Dilligent", false));
    ships.add(new Wc1Ship((byte) 6, "Drayman", false));
    ships.add(new Wc1Ship((byte) 7, "Exeter", false));
    ships.add(new Wc1Ship((byte) 8, "Tiger's Claw", false));
    ships.add(new Wc1Ship((byte) 9, "Salthi", false));
    ships.add(new Wc1Ship((byte) 10, "Dralthi", false));
    ships.add(new Wc1Ship((byte) 11, "Krant", false));
    ships.add(new Wc1Ship((byte) 12, "Gratha", false));
    ships.add(new Wc1Ship((byte) 13, "Jalthi", false));
    ships.add(new Wc1Ship((byte) 14, "Hhriss", false));
    ships.add(new Wc1Ship((byte) 15, "Dorkir", false));
    ships.add(new Wc1Ship((byte) 16, "Lumbari", false));
    ships.add(new Wc1Ship((byte) 17, "Ralari", false));
    ships.add(new Wc1Ship((byte) 18, "Fralthi", false));
    ships.add(new Wc1Ship((byte) 19, "Snakeir", false));
    ships.add(new Wc1Ship((byte) 20, "Sivar", false));
    ships.add(new Wc1Ship((byte) 21, "Starpost", false));
    ships.add(new Wc1Ship((byte) 22, "Asteroids", false));
    ships.add(new Wc1Ship((byte) 23, "Mines", false));
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

  public List<Wc1Ship> listAllFlyable() {
    List<Wc1Ship> result = new ArrayList<>();
    for (Wc1Ship ship : ships) {
      if (ship.isFlyable()) {
        result.add(ship);
      }
    }
    return result;
  }
}
