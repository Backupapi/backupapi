package com.backupapi.api;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.container.PreMatching;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.backupapi.api.responses.UnauthorizedResponse;
import com.backupapi.api.responses.ResponseFactory;

@PreMatching
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
  @Override
  public void filter (ContainerRequestContext requestContext) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    UnauthorizedResponse unauthorizedResponse = ResponseFactory.call(new UnauthorizedResponse());

    requestContext.abortWith(
      Response
        .status(unauthorizedResponse.getStatus())
        .type(MediaType.APPLICATION_JSON)
        .entity(mapper.writeValueAsString(unauthorizedResponse))
        .build()
    );
  }
}