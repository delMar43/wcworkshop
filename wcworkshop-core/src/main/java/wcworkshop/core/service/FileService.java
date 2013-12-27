package wcworkshop.core.service;

import java.io.File;

import wcworkshop.core.config.Configuration;

public class FileService {
  private static final FileService instance = new FileService();

  public File[] listUserFiles(String username, String projectId) {
    File directory = new File(Configuration.getInstance().getGeneratedPath(username, projectId));
    if (!directory.exists()) {
      directory.mkdirs();
    }

    return directory.listFiles();
  }

  private FileService() {
  }

  public static FileService getInstance() {
    return instance;
  }
}
