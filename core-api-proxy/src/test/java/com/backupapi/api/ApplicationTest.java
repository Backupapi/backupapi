package com.backupapi.api;

import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Assert;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

import java.util.UUID;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

import javax.ws.rs.core.Response.Status;

@RunWith(HierarchicalContextRunner.class)
public class ApplicationTest {
  private static Application application;

  @BeforeClass
  public static void setup () throws Exception {
    application = Application.getInstance();
    application.start();
  }

  @AfterClass
  public static void close () throws Exception {
    application.stop();
  }

  @Test
  public void testConnection () throws MalformedURLException, IOException {
    HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:4040/").openConnection();
    http.connect();
    Assert.assertEquals(Status.UNAUTHORIZED.getStatusCode(), http.getResponseCode());
  }
}