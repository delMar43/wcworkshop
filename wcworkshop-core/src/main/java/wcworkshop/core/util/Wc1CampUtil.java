package wcworkshop.core.util;

import wcworkshop.core.data.Wc1CampMedal;
import wcworkshop.core.data.Wc1CampPilot;

public class Wc1CampUtil {

  public String getPilot(byte value) {
    return Wc1CampPilot.getByValue(value).toString();
  }

  public String getMedal(short value) {
    return Wc1CampMedal.getByValue(value).toString();
  }
}
