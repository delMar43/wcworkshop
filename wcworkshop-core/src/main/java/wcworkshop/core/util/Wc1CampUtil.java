package wcworkshop.core.util;

import wcworkshop.core.data.Wc1CampMedal;

public class Wc1CampUtil {

  public String getMedal(short value) {
    return Wc1CampMedal.getByValue(value).toString();
  }
}
