package com.bubbleIM.events;

import com.google.common.base.MoreObjects;

public class SignupEvent {

  private String connectionID;
  private String username;
  private String password;
  private long timestamp;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
        .add("username", username)
        .add("password", password)
        .add("connectionID", connectionID)
        .add("timestamp", timestamp)
        .toString();
  }
}
