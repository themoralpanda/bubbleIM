package com.bubbleIM.events.internal;

import com.bubbleIM.actors.dto.User;
import com.google.common.base.MoreObjects;

public class ValidationSuccessEvent {

  private User user;
  private long timestamp;

  public ValidationSuccessEvent(User user, long timestamp) {
    this.user = user;
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("user", user)
        .add("timestamp", timestamp)
        .toString();
  }
}
