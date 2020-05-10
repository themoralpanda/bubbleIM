package com.bubbleIM.actors.dto;

import com.google.common.base.MoreObjects;
import org.glassfish.grizzly.websockets.WebSocket;

public class User {

  private String username;
  private String password;
  private String connectionID;
  private WebSocket socket;
  private String sessionID;

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

  public WebSocket getSocket() {
    return socket;
  }

  public void setSocket(WebSocket socket) {
    this.socket = socket;
  }

  public String getSessionID() {
    return sessionID;
  }

  public void setSessionID(String sessionID) {
    this.sessionID = sessionID;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("username", username)
        .add("password", password)
        .add("connectionID", connectionID)
        .add("socket", socket)
        .add("sessionID", sessionID)
        .toString();
  }
}
