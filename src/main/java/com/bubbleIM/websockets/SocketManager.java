package com.bubbleIM.websockets;

import akka.event.EventStream;
import com.google.inject.Inject;
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
    logger.info("Created chat application");
  }

  @Override
  public void onConnect(WebSocket socket) {
    logger.info("Connected: {}", socket);
    super.onConnect(socket);
    //Publish a New Socket Request
    eventStream.publish(new NewConnectionRequest(socket, System.currentTimeMillis()));
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
