package com.bubbleIM.events;

import com.google.common.base.MoreObjects;

public class MessageDispatchEvent {

  private String connectionID;
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

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("connectionID", connectionID)
        .add("timestamp", timestamp)
        .add("message", message)
        .toString();
  }
}
