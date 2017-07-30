package com.backupapi.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("{any: .*}")
public class ProxyService {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResponse (
    @Context HttpServletRequest httpServletRequest,
    // @Context SecurityContext securityContext,
    @Context UriInfo uriInfo,
    @Context HttpHeaders httpHeaders
  ) {
    Response response;

    String ipAddress = httpServletRequest.getRemoteAddr();
    System.out.println(ipAddress);

    for (String header : httpHeaders.getRequestHeaders().keySet()) {
      System.out.println(header);
      System.out.println(httpHeaders.getRequestHeader(header).get(0));
    }

    // System.out.println(accessKeyId);
    // System.out.println(accessKeySecret);
    // System.out.println(params);
    // System.out.println(uriInfo.getPath());
    // System.out.println(uriInfo.getRequestUri().getQuery());

    MultivaluedMap<String, String> pathParameters = uriInfo.getPathParameters();
    MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

    for (String param : pathParameters.keySet()) {
      System.out.println(param + ": " + pathParameters.getFirst(param));
    }

    for (String param : queryParameters.keySet()) {
      System.out.println(param + ": " + queryParameters.getFirst(param));
    }

    ObjectMapper mapper = new ObjectMapper();
    Payload payload = new Payload();
    payload.setRequestId("12345");

    try {
      response = Response.status(Status.CREATED).entity(mapper.writeValueAsString(payload)).build();
    } catch (JsonProcessingException ex) {
      response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    return response;
  }
}

class Payload {
  String requestId;

  public void setRequestId (String requestId) {
    this.requestId = requestId;
  }

  public String getRequestId () {
    return requestId;
  }
}
