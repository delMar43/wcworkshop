package wcworkshop.core.util;

public class Wc1NavPointUtil {

  private static final Wc1NavPointUtil instance = new Wc1NavPointUtil();

  private Wc1NavPointUtil() {
  }

  public String getType(byte value) {
    String result;
    switch (value) {
      case (byte) 0:
        result = "Manipulated";
        break;
      case 1:
        result = "Dominant";
        break;
      case 2 - 4:
        result = "Follow up " + value;
        break;
      default:
        result = "Unknown " + value;
    }
    return result;
  }

  public static Wc1NavPointUtil getInstance() {
    return instance;
  }
}
