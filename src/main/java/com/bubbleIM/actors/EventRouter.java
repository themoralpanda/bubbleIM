package com.bubbleIM.actors;

import akka.actor.UntypedAbstractActor;
import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.MessageEvent;
import com.google.inject.Inject;

public class EventRouter extends UntypedAbstractActor {

  @Inject
  private EventStream eventStream;


  public EventRouter() {
    Boot.getInjector().injectMembers(this);
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof MessageEvent) {
      MessageEvent event = (MessageEvent) message;

    }
  }
}
