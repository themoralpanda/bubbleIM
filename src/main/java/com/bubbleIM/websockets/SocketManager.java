package com.bubbleIM.websockets;

import akka.event.EventStream;
import com.bubbleIM.Boot;
import com.bubbleIM.events.NewMessageEvent;
import com.bubbleIM.events.internal.UserConnectionEvent;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.UUID;
import org.glassfish.grizzly.websockets.DataFrame;
import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketManager extends WebSocketApplication {

  private static final Logger logger = LoggerFactory.getLogger(SocketManager.class);
  private HashMap<WebSocket, String> connections;

  @Inject
  private EventStream eventStream;

  public SocketManager() {
    Boot.getInjector().injectMembers(this);
    connections = new HashMap<>();
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
    connections.put(socket, connectionID);
  }

  @Override
  public void onMessage(WebSocket socket, String text) {
    logger.info("String message received: [{}]", text);

    NewMessageEvent event = new NewMessageEvent();
    event.setConnectionID(connections.get(socket));
    event.setTimestamp(System.currentTimeMillis());
    event.setMessage(text);
    event.setSocket(socket);

    eventStream.publish(event);
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
