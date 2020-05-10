package com.bubbleIM;

import akka.actor.ActorSystem;
import com.bubbleIM.actors.AkkaModule;
import com.bubbleIM.http.Server;
import com.bubbleIM.websockets.SocketManager;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.grizzly.websockets.WebSocketApplication;

public class Boot {

  private static ActorSystem actorSystem;
  private static Injector injector;

  public void startup() {
    //Start Http server
    actorSystem = ActorSystem.create("BubbleIM");

    injector = Guice.createInjector(
        new AbstractModule() {
          @Override
          protected void configure() {
            bind(ActorSystem.class).toInstance(actorSystem);
          }
        },
        new AkkaModule());
    Server httpServer = new Server();
    WebSocketApplication chatApp = new SocketManager();
    httpServer.initWebSockets(chatApp);
    httpServer.startUp();

    injector.injectMembers(this);
    Runtime.getRuntime().addShutdownHook(new Thread(
        () -> {
          httpServer.shutdown();
        }
    ));
  }

  public static Injector getInjector() {
    return injector;
  }

  public static ActorSystem getActorSystem() {
    return actorSystem;
  }
}
