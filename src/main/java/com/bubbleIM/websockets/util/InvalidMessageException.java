package com.bubbleIM.websockets.util;

import org.glassfish.grizzly.websockets.WebSocket;

public class InvalidMessageException extends Exception {

  private String connectionID;
  private WebSocket socket;

  public InvalidMessageException(String message, String connectionID,
      WebSocket socket) {
    super(message);
    this.connectionID = connectionID;
    this.socket = socket;
  }

  public String getConnectionID() {
    return connectionID;
  }

  public WebSocket getSocket() {
    return socket;
  }
}
