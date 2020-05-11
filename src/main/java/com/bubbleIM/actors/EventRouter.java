package com.bubbleIM.actors;

import static com.bubbleIM.events.SocketEventType.LOGIN;

import akka.actor.UntypedAbstractActor;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.LoginEvent;
import com.bubbleIM.events.MessageDispatchEvent;
import com.bubbleIM.events.NewMessageEvent;
import com.bubbleIM.events.SignupEvent;
import com.bubbleIM.events.SocketMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventRouter extends UntypedAbstractActor {

  private static final Logger logger = LoggerFactory.getLogger(EventRouter.class);

  @Inject
  private EventStream eventStream;

  @Inject
  private ObjectMapper objectMapper;

  public EventRouter() {
    Boot.getInjector().injectMembers(this);
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof NewMessageEvent) {
      NewMessageEvent event = (NewMessageEvent) message;
      SocketMessage socketMessage = objectMapper.readValue(event.getMessage(), SocketMessage.class);
      if (!isValidMessage(socketMessage)) {
        logger.error("Received invalid socket message {} from socket with id {}", socketMessage,
            event.getConnectionID());
        event.getSocket().close();
      }
      switch (socketMessage.getType()) {
        case SIGNUP:
          SignupMessage signupMessage = objectMapper
              .convertValue(socketMessage.getContent(), SignupMessage.class);

          SignupEvent signupEvent = new SignupEvent();
          signupEvent.setConnectionID(event.getConnectionID());
          signupEvent.setUsername(signupMessage.getUsername());
          signupEvent.setPassword(signupMessage.getPassword());
          signupEvent.setTimestamp(event.getTimestamp());

          eventStream.publish(signupEvent);
          break;
        case LOGIN:
          LoginMessage loginMessage = objectMapper
              .convertValue(socketMessage.getContent(), LoginMessage.class);

          LoginEvent loginEvent = new LoginEvent();
          loginEvent.setConnectionID(event.getConnectionID());
          loginEvent.setUsername(loginMessage.getUsername());
          loginEvent.setPassword(loginMessage.getPassword());
          loginEvent.setTimestamp(event.getTimestamp());

          eventStream.publish(loginEvent);
          break;
        case MESSAGE:
          ContentMessage contentMessage = objectMapper
              .convertValue(socketMessage.getContent(), ContentMessage.class);

          MessageDispatchEvent messageDispatchEvent = new MessageDispatchEvent();
          messageDispatchEvent.setConnectionID(event.getConnectionID());
          messageDispatchEvent.setMessage(contentMessage.getText());
          messageDispatchEvent.setTimestamp(event.getTimestamp());

          eventStream.publish(messageDispatchEvent);
          break;
      }
    }
  }

  private boolean isValidMessage(SocketMessage message) {
    switch (message.getType()) {
      case SIGNUP:
        SignupMessage signupMessage = null;
        try {
          signupMessage = objectMapper
              .convertValue(message.getContent(), SignupMessage.class);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }

        if (!Strings.isNullOrEmpty(signupMessage.getUsername()) && !Strings
            .isNullOrEmpty(signupMessage.getPassword())) {
          return true;
        }

        return false;
      case LOGIN:
        LoginMessage loginMessage = null;
        try {
          loginMessage = objectMapper
              .convertValue(message.getContent(), LoginMessage.class);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }

        if (!Strings.isNullOrEmpty(loginMessage.getUsername()) && !Strings
            .isNullOrEmpty(loginMessage.getPassword())) {
          return true;
        }

        return false;
      case MESSAGE:
        ContentMessage contentMessage = null;

        try {
          contentMessage = objectMapper
              .convertValue(message.getContent(), ContentMessage.class);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }

        if (!Strings.isNullOrEmpty(contentMessage.getText())) {
          return true;
        }
        return false;
      case POLL:
        //TODO handle polling event.
        return true;
      default:
        return false;
    }
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

  private static class LoginMessage {

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

  private static class ContentMessage {

    private String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("text", text)
          .toString();
    }
  }

  private static class PollMessage {

    private String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("text", text)
          .toString();
    }
  }
}
