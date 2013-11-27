package wcworkshop.core.config;

public class Configuration {
  private static final Configuration instance = new Configuration();

  private Configuration() {
  }

  public String getResourcePath() {
    String result;
    if (isWindows()) {
      result = "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/";
    } else {
      result = "/opt/wcworkshop/";
    }
    return result;
  }

  private String getOs() {
    String os = System.getProperty("os.name").toLowerCase();
    return os;
  }

  private boolean isWindows() {
    return getOs().contains("win");
  }

  private boolean isLinux() {
    return !getOs().contains("win");
  }

  public static Configuration getInstance() {
    return instance;
  }
}
