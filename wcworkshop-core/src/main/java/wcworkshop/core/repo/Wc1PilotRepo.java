package wcworkshop.core.repo;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1Pilot;

public class Wc1PilotRepo {
  private static final Wc1PilotRepo instance = new Wc1PilotRepo();

  private List<Wc1Pilot> pilots = new ArrayList<>();

  private Wc1PilotRepo() {
    pilots.add(new Wc1Pilot((byte) -1, "Empty"));
    pilots.add(new Wc1Pilot((byte) 0, "Spirit"));
    pilots.add(new Wc1Pilot((byte) 1, "Hunter"));
    pilots.add(new Wc1Pilot((byte) 2, "Bossman"));
    pilots.add(new Wc1Pilot((byte) 3, "Iceman"));
    pilots.add(new Wc1Pilot((byte) 4, "Angel"));
    pilots.add(new Wc1Pilot((byte) 5, "Paladin"));
    pilots.add(new Wc1Pilot((byte) 6, "Maniac"));
    pilots.add(new Wc1Pilot((byte) 7, "Knight"));
    pilots.add(new Wc1Pilot((byte) 11, "Unknown (11)"));
    pilots.add(new Wc1Pilot((byte) 12, "Unknown (12)"));
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
