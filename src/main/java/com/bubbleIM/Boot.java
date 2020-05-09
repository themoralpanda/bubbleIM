package com.bubbleIM;

import akka.actor.ActorSystem;
import com.bubbleIM.events.EventModule;
import com.bubbleIM.http.Server;
import com.bubbleIM.websockets.SocketManager;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.grizzly.websockets.WebSocketApplication;

public class Boot {

  private static Injector injector;

  public static void main(String args[]) {
    //Start Http server
    ActorSystem actorSystem = ActorSystem.create("BubbleIM");

    injector = Guice.createInjector(
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(ActorSystem.class).toInstance(actorSystem);
          }
        },
        new EventModule());

    Server httpServer = new Server();
    WebSocketApplication chatApp = new SocketManager();
    httpServer.initWebSockets(chatApp);
    httpServer.startUp();

    Runtime.getRuntime().addShutdownHook(new Thread(
        () -> {
          httpServer.shutdown();
        }
    ));
  }

  public static Injector getInjector() {
    return injector;
  }
}
