package com.bubbleIM.websockets;

import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.internal.UserConnectionEvent;
import com.google.inject.Inject;
import java.util.UUID;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketManager extends WebSocketApplication {

  private static final Logger logger = LoggerFactory.getLogger(SocketManager.class);

  @Inject
  private EventStream eventStream;

  public SocketManager() {
    Boot.getInjector().injectMembers(this);
    logger.info("Created chat application");
  }

  @Override
  public void onConnect(WebSocket socket) {
    logger.info("Connected: {}", socket);
    super.onConnect(socket);
    //Publish a New Socket Request
    String connectionID = UUID.randomUUID().toString();
    eventStream
        .publish(new UserConnectionEvent(socket, System.currentTimeMillis(), connectionID, false));
  }

  @Override
  public void onMessage(WebSocket socket, String text) {
    logger.info("String message received: [{}]", text);
    socket.broadcast(getWebSockets(), text);
  }

  @Override
  public void onMessage(WebSocket socket, byte[] bytes) {
    logger.info("Byte message received: length={}", bytes.length);
    socket.broadcast(getWebSockets(), bytes);
  }

  @Override
  public void onClose(WebSocket socket, DataFrame frame) {
    super.onClose(socket, frame);
  }
}
