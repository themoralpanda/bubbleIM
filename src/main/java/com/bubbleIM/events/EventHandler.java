package com.bubbleIM.events;

import com.bubbleIM.events.SocketEvent;

public interface EventHandler {

  void handleEvent(SocketEvent event);
}
