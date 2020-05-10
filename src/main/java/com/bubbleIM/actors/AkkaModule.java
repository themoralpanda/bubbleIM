package com.bubbleIM.actors;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.LoginEvent;
import com.bubbleIM.events.MessageEvent;
import com.bubbleIM.events.SignupEvent;
import com.bubbleIM.events.internal.UserConnectionEvent;
import com.bubbleIM.events.internal.ValidationSuccessEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.util.Arrays;

public class AkkaModule extends AbstractModule {

  public AkkaModule() {
    this.actorSystem = Boot.getActorSystem();
  }

  private ActorSystem actorSystem;

  @Override
  protected void configure() {

    bind(ObjectMapper.class).toProvider(() -> new ObjectMapper()).in(Singleton.class);

    createActor(ConnectionValidator.class, UserConnectionEvent.class, SignupEvent.class,
        LoginEvent.class);
    createActor(MessageProcessor.class, ValidationSuccessEvent.class, MessageEvent.class);

    bind(EventStream.class).toProvider(new Provider<EventStream>() {
      @Inject
      private ActorSystem actorSystem;

      @Override
      public EventStream get() {
        return actorSystem.getEventStream();
      }
    });
  }

  private void createActor(final Class<? extends Actor> actorClass, Class<?>... events) {
    ActorRef actorRef = actorSystem.actorOf(Props.create(actorClass));
    Arrays.stream(events).forEach(e -> {
      actorSystem.getEventStream().subscribe(actorRef, e);
    });
  }
}
