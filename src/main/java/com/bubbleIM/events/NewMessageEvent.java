package com.bubbleIM.events;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class NewMessageEvent {

  private String connectionID;
  private WebSocket socket;
  private long timestamp;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getConnectionID() {
    return connectionID;
  }

  public void setConnectionID(String connectionID) {
    this.connectionID = connectionID;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public WebSocket getSocket() {
    return socket;
  }

  public void setSocket(WebSocket socket) {
    this.socket = socket;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("connectionID", connectionID)
        .add("timestamp", timestamp)
        .add("message", message)
        .add("socket", socket)
        .toString();
  }
}
