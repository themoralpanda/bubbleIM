package com.bubbleIM.actors;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.bubbleIM.events.LoginEvent;
import com.bubbleIM.events.MessageDispatchEvent;
import com.bubbleIM.events.NewMessageEvent;
import com.bubbleIM.events.SignupEvent;
import com.bubbleIM.events.internal.UserConnectionEvent;
import com.bubbleIM.events.internal.ValidationSuccessEvent;
import java.util.Arrays;

public class ActorSystem {

  private static akka.actor.ActorSystem actorSystem;
  private static final String ACTOR_SYSTEM_NAME = "bubbleIM";

  static {
    actorSystem = akka.actor.ActorSystem.create(ACTOR_SYSTEM_NAME);
  }


  public static void init() {
    createActor(ConnectionValidator.class, UserConnectionEvent.class, SignupEvent.class,
        LoginEvent.class);
    createActor(MessageProcessor.class, ValidationSuccessEvent.class, MessageDispatchEvent.class);

    createActor(EventRouter.class, NewMessageEvent.class);
  }

  private static void createActor(final Class<? extends Actor> actorClass, Class<?>... events) {
    ActorRef actorRef = actorSystem.actorOf(Props.create(actorClass));
    Arrays.stream(events).forEach(e -> {
      actorSystem.getEventStream().subscribe(actorRef, e);
    });
  }

  public static akka.actor.ActorSystem getActorSystem() {
    return actorSystem;
  }
}
