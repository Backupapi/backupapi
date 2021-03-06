package com.backupapi.api;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {
  @Override
  public void filter(
    ContainerRequestContext requestContext,
    ContainerResponseContext responseContext
  ) throws IOException {
    // MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    // headers.add("Access-Control-Allow-Headers", "Content-Type");
  }
}