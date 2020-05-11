package com.bubbleIM.http;

import com.bubbleIM.events.SocketMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import org.testng.annotations.Test;

public class JacksonTest {

  @Test
  public void testJacksonSample() {
    String json = "{\"type\":\"SIGNUP\",\"content\":{\"username\":\"vig\",\"password\":\"pwd\"}}";
    ObjectMapper mapper = new ObjectMapper();
    SocketMessage socketMessage = null;
    try {
      socketMessage = mapper.readValue(json, SocketMessage.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    SignupMessage msg = mapper.convertValue(socketMessage.getContent(), SignupMessage.class);
    int a = 2;
  }

  private static class SignupMessage {

    private String username;
    private String password;

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

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("username", username)
          .add("password", password)
          .toString();
    }
  }
}
