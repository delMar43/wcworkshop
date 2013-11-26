package wcworkshop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

  private static final Logger logger = LoggerFactory.getLogger(ServerMain.class);

  public static void main(String[] args) {
    try {
      JettyServer server = new JettyServer(8080);
      server.start();
      logger.info("Server started");
      server.join();
    } catch (Exception e) {
      logger.error("Failed to start server.", e);
    }
  }
}
