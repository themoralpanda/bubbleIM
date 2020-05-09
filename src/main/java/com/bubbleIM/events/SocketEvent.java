package com.bubbleIM.events;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class SocketEvent {

  private String socketID;
  private WebSocket socket;
  private SocketEventType type;
  private long timestamp;
  private Object content;

  public SocketEventType getType() {
    return type;
  }

  public void setType(SocketEventType type) {
    this.type = type;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  public String getSocketID() {
    return socketID;
  }

  public void setSocketID(String socketID) {
    this.socketID = socketID;
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
        .add("socketID", socketID)
        .add("type", type)
        .add("timestamp", timestamp)
        .add("content", content)
        .toString();
  }
}

