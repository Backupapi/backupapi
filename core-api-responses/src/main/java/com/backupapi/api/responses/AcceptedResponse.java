package com.backupapi.api.responses;

import javax.ws.rs.core.Response.Status;

public class AcceptedResponse extends Response {
  @Override
  protected void setStatus () {
    this.status = Status.ACCEPTED.getStatusCode();
  }
}