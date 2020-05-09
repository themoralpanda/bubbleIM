package com.bubbleIM.actors;

import akka.actor.ActorSystem;
import akka.event.EventStream;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AkkaModule extends AbstractModule {

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
