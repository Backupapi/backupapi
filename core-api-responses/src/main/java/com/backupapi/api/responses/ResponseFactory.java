package com.backupapi.api.responses;

import java.util.UUID;

public class ResponseFactory {
  public static <T extends Response> T call (T response) {
    UUID uuid = UUID.randomUUID();

    response.setRequestId(uuid.toString());
    response.setTimestamp(System.currentTimeMillis());
    response.setStatus();

    return response;
  }
}