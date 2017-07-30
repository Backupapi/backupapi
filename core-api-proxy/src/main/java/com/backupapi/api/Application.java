package com.backupapi.api;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Application {
  private static Application instance;
  private Server server;

  public static void main (String[] args) throws Exception {
    try {
      getInstance();
      getInstance().start();
      getInstance().join();
    } finally {
      getInstance().stop();
    }
  }

  public static Application getInstance () {
    if (instance == null) {
      instance = new Application();
    }

    return instance;
  }

  public Application () {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    this.server = new Server(4040);
    server.setHandler(context);

    ResourceConfig config = new ResourceConfig()
      .packages("jersey.jetty.embedded")
      .register(AuthenticationFilter.class)
      .register(CORSFilter.class)
      .register(ProxyService.class);

    ServletHolder servlet = new ServletHolder(new ServletContainer(config));
    servlet.setInitOrder(0);

    context.addServlet(
      servlet,
      "/*"
    );
  }

  public void start () throws Exception {
    server.start();
  }

  public void join () throws Exception {
    server.join();
  }

  public void stop () throws Exception {
    server.stop();
    server.destroy();
  }
}