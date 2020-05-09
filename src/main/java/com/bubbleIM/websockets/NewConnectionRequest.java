package com.bubbleIM.websockets;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class NewConnectionRequest {

  private WebSocket socket;
  private long timestamp;

  public NewConnectionRequest(WebSocket socket, long timestamp) {
    this.socket = socket;
    this.timestamp = timestamp;
  }

  public WebSocket getSocket() {
    return socket;
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("socket", socket)
        .add("timestamp", timestamp)
        .toString();
  }
}
