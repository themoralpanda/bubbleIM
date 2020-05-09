package com.bubbleIM.actors;

import akka.actor.UntypedAbstractActor;
import com.bubbleIM.events.SignupEvent;

public class SignupHandler extends UntypedAbstractActor {

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof SignupEvent) {

    }
  }
}
