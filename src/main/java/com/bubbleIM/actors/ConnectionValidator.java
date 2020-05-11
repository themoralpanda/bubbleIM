package com.bubbleIM.actors;

import akka.actor.UntypedAbstractActor;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.actors.dto.User;
import com.bubbleIM.events.LoginEvent;
import com.bubbleIM.events.SignupEvent;
import com.bubbleIM.events.internal.ValidationSuccessEvent;

import com.bubbleIM.events.internal.UserConnectionEvent;
import com.google.inject.Inject;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A connection becomes valid, at once it receives either Signup Event or Login Event (which inturn
 * is successful)
 */
public class ConnectionValidator extends UntypedAbstractActor {

  private static final Logger logger = LoggerFactory.getLogger(ConnectionValidator.class);
  private HashMap<String, UserConnectionEvent> unvalidatedConnections;

  @Inject
  private EventStream eventStream;

  public ConnectionValidator() {
    Boot.getInjector().injectMembers(this);
    unvalidatedConnections = new HashMap<>();
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof UserConnectionEvent) {
      UserConnectionEvent event = (UserConnectionEvent) message;
      logger.info("Received New UserConnectionEvent with id {}", event.getConnectionID());
      unvalidatedConnections.put(event.getConnectionID(), event);
    } else if (message instanceof SignupEvent) {
      //TODO Check if email is already taken or not
      //TODO Generate code and send it to user
      SignupEvent event = (SignupEvent) message;

      String connectionID = event.getConnectionID();

      User user = new User();
      user.setConnectionID(connectionID);
      user.setUsername(event.getUsername());
      user.setPassword(event.getPassword());
      user.setSocket(unvalidatedConnections.get(connectionID).getSocket());

      unvalidatedConnections.remove(connectionID);

      eventStream.publish(new ValidationSuccessEvent(user, event.getTimestamp()));

    } else if (message instanceof LoginEvent) {
      //TODO handle login validation logic - currently all are validated
      LoginEvent event = (LoginEvent) message;

      String connectionID = event.getConnectionID();

      User user = new User();
      user.setConnectionID(connectionID);
      user.setUsername(event.getUsername());
      user.setPassword(event.getPassword());
      user.setSocket(unvalidatedConnections.get(connectionID).getSocket());

      unvalidatedConnections.remove(connectionID);

      eventStream.publish(new ValidationSuccessEvent(user, event.getTimestamp()));
    } else {
      super.unhandled(message);
    }
  }
}
