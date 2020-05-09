package com.bubbleIM.events;

public class MessageEvent extends SocketEvent {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return super.getToStringHelper()
        .add("message", message)
        .toString();
  }
}
