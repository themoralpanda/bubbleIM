package com.bubbleIM.actors;

import akka.actor.UntypedAbstractActor;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.actors.dto.User;
import com.bubbleIM.events.MessageDispatchEvent;
import com.bubbleIM.events.internal.ValidationSuccessEvent;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.glassfish.grizzly.websockets.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessor extends UntypedAbstractActor {

  private static final Logger logger = LoggerFactory.getLogger(ConnectionValidator.class);
  private Set<WebSocket> sockets;
  private HashMap<String, User> validatedUsers;

  @Inject
  private EventStream eventStream;

  public MessageProcessor() {
    sockets = new HashSet<>();
    validatedUsers = new HashMap<>();
    Boot.getInjector().injectMembers(this);
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof ValidationSuccessEvent) {
      ValidationSuccessEvent event = (ValidationSuccessEvent) message;
      User validatedUser = event.getUser();
      validatedUsers.put(validatedUser.getConnectionID(), validatedUser);
      sockets.add(validatedUser.getSocket());
    } else if (message instanceof MessageDispatchEvent) {
      MessageDispatchEvent event = (MessageDispatchEvent) message;
      String senderID = event.getConnectionID();
      validatedUsers.get(senderID).getSocket().broadcast(sockets, event.getMessage());
    } else {
      super.unhandled(message);
    }
  }
}
