package com.bubbleIM.events;

import com.google.common.base.MoreObjects;

public class SocketMessage {

  private SocketEventType type;
  private Object content;

  public SocketEventType getType() {
    return type;
  }

  public void setType(SocketEventType type) {
    this.type = type;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("type", type)
        .add("content", content)
        .toString();
  }
}

