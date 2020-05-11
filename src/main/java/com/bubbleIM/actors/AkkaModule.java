package com.bubbleIM.actors;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.LoginEvent;
import com.bubbleIM.events.MessageDispatchEvent;
import com.bubbleIM.events.NewMessageEvent;
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
  }

  private ActorSystem actorSystem;

  @Override
  protected void configure() {

    bind(EventStream.class).toProvider(new Provider<EventStream>() {
      @Inject
      private ActorSystem actorSystem;

      @Override
      public EventStream get() {
        return actorSystem.getEventStream();
      }
    });


  }


}
