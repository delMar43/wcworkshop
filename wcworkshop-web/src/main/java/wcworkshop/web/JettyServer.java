package wcworkshop.web;

import java.io.File;
import java.net.URL;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

public class JettyServer {
  private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);

  public static final String WEB_APP_ROOT = "webapp"; // that folder has to be just somewhere in classpath
  public static final String MVC_SERVLET_NAME = "wcworkshop";
  public static final String JSP_SERVLET_NAME = "jspServlet";

  private Server server;
  private final int port;

  public JettyServer(int port) {
    this.port = port;
  }

  public void start() {
    server = new Server(port);
    HandlerList handlerList = new HandlerList();
    handlerList.setHandlers(new Handler[] { getResourceHandler(), getServletHandler(), /* new DefaultHandler() */});
    server.setHandler(handlerList);

    try {
      server.start();
    } catch (Exception e) {
      logger.error("Failed to start server", e);
      throw new RuntimeException();
    }

    logger.info("Server started");
  }

  private Handler getResourceHandler() {
    ResourceHandler result = new ResourceHandler();

    result.setDirectoriesListed(false);
    result.setWelcomeFiles(new String[] { "index.html" });
    result.setResourceBase(getBaseUrl());

    return result;
  }

  private ServletContextHandler getServletHandler() {
    ServletHolder mvcServletHolder = new ServletHolder(MVC_SERVLET_NAME, new DispatcherServlet());
    // mvcServletHolder.setInitParameter("contextConfigLocation", "web-context.xml");

    ServletHolder jspServletHolder = new ServletHolder(JSP_SERVLET_NAME, new JspServlet());
    // these two lines are not strictly required - they will keep classes generated from JSP in "${javax.servlet.context.tempdir}/views/generated"
    jspServletHolder.setInitParameter("keepgenerated", "true");
    jspServletHolder.setInitParameter("scratchDir", "views/generated");

    // session has to be set, otherwise Jasper won't work
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setAttribute("javax.servlet.context.tempdir", new File("../tmp/webapp"));
    // that classloader is requres to set JSP classpath. Without it you will just get NPE
    context.setClassLoader(Thread.currentThread().getContextClassLoader());
    context.addServlet(jspServletHolder, "*.jsp");
    context.addServlet(mvcServletHolder, "*.html");
    context.setResourceBase(getBaseUrl());

    return context;
  }

  public void join() throws InterruptedException {
    server.join();
  }

  private String getBaseUrl() {
    URL webInfUrl = JettyServer.class.getClassLoader().getResource(WEB_APP_ROOT);
    if (webInfUrl == null) {
      throw new RuntimeException("Failed to find web application root: " + WEB_APP_ROOT);
    }
    return webInfUrl.toExternalForm();
  }
}
