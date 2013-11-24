package wcworkshop.core.util;

import wcworkshop.core.data.Wc1CampPilot;

public class Wc1CampPilotUtil {

  public String get(byte value) {
    return Wc1CampPilot.getByValue(value).toString();
  }
}
