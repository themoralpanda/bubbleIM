package com.bubbleIM.actors;

import static com.bubbleIM.events.SocketEventType.LOGIN;
import static com.bubbleIM.events.SocketEventType.MESSAGE;
import static com.bubbleIM.events.SocketEventType.POLL;
import static com.bubbleIM.events.SocketEventType.SIGNUP;

import akka.actor.UntypedAbstractActor;
import akka.event.EventStream;
import com.bubbleIM.events.SignupEvent;
import com.bubbleIM.events.SocketEvent;
import com.bubbleIM.events.SocketValidationFailureEvent;
import com.google.inject.Inject;

public class EventValidator extends UntypedAbstractActor {

  @Inject
  private EventStream eventStream;

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof SocketEvent) {
      SocketEvent event = (SocketEvent) message;
      switch (event.getType()) {
        case LOGIN:
        case SIGNUP:
          if (event.getContent() instanceof SignupEvent) {

          } else {
          }
      }
      case MESSAGE:
      case POLL:
        break;
      default:
        eventStream.publish(new SocketValidationFailureEvent(event.getSocketID()));
        break;
    }
  } else

  {
    super.unhandled(message);
  }
}
}
