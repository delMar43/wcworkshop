package wcworkshop.core.repo;

import java.util.HashMap;
import java.util.Map;

import wcworkshop.core.data.Wc1Pilot;

public class Wc1PilotRepo {
  private static final Wc1PilotRepo instance = new Wc1PilotRepo();

  private Map<Byte, Wc1Pilot> pilots = new HashMap<>();

  private Wc1PilotRepo() {
    pilots.put((byte) -1, new Wc1Pilot((byte) -1, "Empty"));
    pilots.put((byte) 0, new Wc1Pilot((byte) 0, "Spirit"));
    pilots.put((byte) 1, new Wc1Pilot((byte) 1, "Hunter"));
    pilots.put((byte) 2, new Wc1Pilot((byte) 2, "Bossman"));
    pilots.put((byte) 3, new Wc1Pilot((byte) 3, "Iceman"));
    pilots.put((byte) 4, new Wc1Pilot((byte) 4, "Angel"));
    pilots.put((byte) 5, new Wc1Pilot((byte) 5, "Paladin"));
    pilots.put((byte) 6, new Wc1Pilot((byte) 6, "Maniac"));
    pilots.put((byte) 7, new Wc1Pilot((byte) 7, "Knight"));
    pilots.put((byte) 11, new Wc1Pilot((byte) 11, "Unknown (11)"));
    pilots.put((byte) 12, new Wc1Pilot((byte) 12, "Unknown (12)"));
  }

  public Wc1Pilot getPilot(byte value) {
    if (value >= pilots.size()) {
      throw new RuntimeException("Unknown pilot: " + value);
    }
    return pilots.get(value);
  }

  public static Wc1PilotRepo getInstance() {
    return instance;
  }
}
