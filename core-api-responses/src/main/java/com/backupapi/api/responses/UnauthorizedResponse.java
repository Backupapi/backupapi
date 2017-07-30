package com.backupapi.api.responses;

import javax.ws.rs.core.Response.Status;

public class UnauthorizedResponse extends Response {
  @Override
  protected void setStatus () {
    this.status = Status.UNAUTHORIZED.getStatusCode();
  }
}