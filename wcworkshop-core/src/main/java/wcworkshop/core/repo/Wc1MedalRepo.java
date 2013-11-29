package wcworkshop.core.repo;

import java.util.ArrayList;
import java.util.List;

import wcworkshop.core.data.Wc1Medal;

public class Wc1MedalRepo {
  private static final Wc1MedalRepo instance = new Wc1MedalRepo();

  private List<Wc1Medal> medals = new ArrayList<>();

  private Wc1MedalRepo() {
    medals.add(new Wc1Medal((byte) 0, "Bronze Star"));
    medals.add(new Wc1Medal((byte) 1, "Silver Star"));
    medals.add(new Wc1Medal((byte) 2, "Gold Star"));
    medals.add(new Wc1Medal((byte) 3, "Golden Sun"));
    medals.add(new Wc1Medal((byte) 4, "Valor"));
  }

  public Wc1Medal getMedal(byte value) {
    if (value >= medals.size()) {
      throw new RuntimeException("Unknown medal: " + value);
    }
    return medals.get(value);
  }

  public static Wc1MedalRepo getInstance() {
    return instance;
  }
}
