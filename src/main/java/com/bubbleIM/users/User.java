package com.bubbleIM.users;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

/**
 * A User represents an individual user in the BubbleIM Messenger.
 */
public class User {

  private String userID;
  private long lastLoginTimestamp;
  private String username;
  private String password;
  private Status status;
  /**
   * The current active socket connection associated with the user.
   */
  private WebSocket websocket;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

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

  public WebSocket getWebsocket() {
    return websocket;
  }

  public void setWebsocket(WebSocket websocket) {
    this.websocket = websocket;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public long getLastLoginTimestamp() {
    return lastLoginTimestamp;
  }

  public void setLastLoginTimestamp(long lastLoginTimestamp) {
    this.lastLoginTimestamp = lastLoginTimestamp;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("userID", userID)
        .add("lastLoginTimestamp", lastLoginTimestamp)
        .add("username", username)
        .add("password", password)
        .add("status", status)
        .add("websocket", websocket)
        .toString();
  }
}
