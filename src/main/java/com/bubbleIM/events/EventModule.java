package com.bubbleIM.events;

import akka.event.EventStream;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

public class EventModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(EventHandler.class).to(EventHandlerImpl.class).in(Singleton.class);
  }
}
