package com.bubbleIM.actors.dto;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class SocketConnection {

  private String identifier;
  private WebSocket webSocket;
  private boolean isAuthenticated;
  private long timestamp;

  public SocketConnection(String identifier, WebSocket webSocket, boolean isAuthenticated,
      long timestamp) {
    this.webSocket = webSocket;
    this.identifier = identifier;
    this.isAuthenticated = isAuthenticated;
    this.timestamp = timestamp;
  }

  public WebSocket getWebSocket() {
    return webSocket;
  }

  public String getIdentifier() {
    return identifier;
  }

  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("webSocket", webSocket)
        .add("identifier", identifier)
        .add("timestamp", timestamp)
        .toString();
  }
}
