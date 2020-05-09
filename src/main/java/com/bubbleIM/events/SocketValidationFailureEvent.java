package com.bubbleIM.events;

import com.google.common.base.MoreObjects;

public class SocketValidationFailureEvent {

  private String socketID;

  public SocketValidationFailureEvent(String socketID) {
    this.socketID = socketID;
  }

  public String getSocketID() {
    return socketID;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("socketID", socketID)
        .toString();
  }
}
