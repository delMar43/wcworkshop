package wcworkshop.core.config;

import java.io.File;

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

  public String getGeneratedPath(String username) {
    return getResourcePath() + "/" + "data" + File.separator + username + File.separator + "generated" + File.separator;
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
