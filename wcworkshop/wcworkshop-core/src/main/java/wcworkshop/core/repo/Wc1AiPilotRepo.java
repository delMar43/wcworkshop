package wcworkshop.core.repo;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1AiPilot;

public class Wc1AiPilotRepo {
  private static final Wc1AiPilotRepo instance = new Wc1AiPilotRepo();
  private List<Wc1AiPilot> pilots = new ArrayList<>();

  private Wc1AiPilotRepo() {
    pilots.add(new Wc1AiPilot((byte) 0, "Generic Pilot 1"));
    pilots.add(new Wc1AiPilot((byte) 1, "Generic Pilot 2"));
    pilots.add(new Wc1AiPilot((byte) 2, "Generic Pilot 3"));
    pilots.add(new Wc1AiPilot((byte) 3, "Generic Pilot 4"));
    pilots.add(new Wc1AiPilot((byte) 4, "Generic Pilot 5"));
    pilots.add(new Wc1AiPilot((byte) 5, "Spirit"));
    pilots.add(new Wc1AiPilot((byte) 6, "Hunter"));
    pilots.add(new Wc1AiPilot((byte) 7, "Bossman"));
    pilots.add(new Wc1AiPilot((byte) 8, "Iceman"));
    pilots.add(new Wc1AiPilot((byte) 9, "Angel"));
    pilots.add(new Wc1AiPilot((byte) 10, "Paladin"));
    pilots.add(new Wc1AiPilot((byte) 11, "Maniac"));
    pilots.add(new Wc1AiPilot((byte) 12, "Knight"));
    pilots.add(new Wc1AiPilot((byte) 13, "Blair"));
    pilots.add(new Wc1AiPilot((byte) 14, "Bhurak Starkiller"));
    pilots.add(new Wc1AiPilot((byte) 15, "Dakhath Deathstroke"));
    pilots.add(new Wc1AiPilot((byte) 16, "Khajja The Fang"));
    pilots.add(new Wc1AiPilot((byte) 17, "Bakhtosh Redclaw"));
  }

  public Wc1AiPilot getAiPilot(byte value) {
    if (value >= pilots.size()) {
      throw new RuntimeException("Unknown pilot: " + value);
    }
    return pilots.get(value);
  }

  public static Wc1AiPilotRepo getInstance() {
    return instance;
  }
}
