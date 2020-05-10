package com.bubbleIM.events.internal;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class UserConnectionEvent {

  private boolean isAuthenticated;
  private String connectionID;
  private WebSocket socket;
  private long timestamp;

  public UserConnectionEvent(WebSocket socket, long timestamp, String connectionID,
      boolean isAuthenticated) {
    this.socket = socket;
    this.timestamp = timestamp;
    this.connectionID = connectionID;
    this.isAuthenticated = isAuthenticated;
  }

  public WebSocket getSocket() {
    return socket;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getConnectionID() {
    return connectionID;
  }

  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    isAuthenticated = authenticated;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("socket", socket)
        .add("timestamp", timestamp)
        .add("connectionID", connectionID)
        .add("isAuthenticated", isAuthenticated)
        .toString();
  }
}
