package wcworkshop.core.data;

public enum Wc1ModuleShip {
  //@formatter:off
  HORNET((short)0),
  RAPIER((short)1),
  SCIMITAR((short)2),
  RAPTOR((short)3),
  VENTURE((short)4),
  DILLIGENT((short)5),
  DRAYMAN((short)6),
  EXETER((short)7),
  TIGERSCLAW((short)8),
  SALTHI((short)9),
  DRALTHI((short)10),
  KRANT((short)11),
  GRATHA((short)12),
  JALTHI((short)13),
  HHRISS((short)14),
  DORKIR((short)15),
  LUMBARI((short)16),
  RALARI((short)17),
  FRALTHI((short)18),
  SNAKEIR((short)19),
  SIVAR((short)20),
  STARPOST((short)21),
  ASTEROIDS((short)22),
  MINES((short)23),
  UNKNOWN_24((short)24),
  MISSILE((short)29);

  private short value;

  public static Wc1ModuleShip getByValue(short value) {
    for (Wc1ModuleShip ship : values()) {
      if (ship.value == value) {
        return ship;
      }
    }
    throw new RuntimeException("Unknown ship: " + value);
  }
  
  private Wc1ModuleShip(short value) {
    this.value=value;
  }
}
