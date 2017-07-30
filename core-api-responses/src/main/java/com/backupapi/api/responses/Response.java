package com.backupapi.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "status" })
abstract public class Response {
  protected String requestId;
  protected int status;
  protected String checksum;
  protected long timestamp;

  public int getStatus () {
    return status;
  }

  abstract protected void setStatus();

  public String getRequestId () {
    return requestId;
  }

  public void setRequestId (String requestId) {
    this.requestId = requestId;
  }

  public String getChecksum() {
    return checksum;
  }

  public void setChecksum (String checksum) {
    this.checksum = checksum;
  }

  public long getTimestamp () {
    return timestamp;
  }

  public void setTimestamp (long timestamp) {
    this.timestamp = timestamp;
  }
}