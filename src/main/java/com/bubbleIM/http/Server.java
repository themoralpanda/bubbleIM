package com.bubbleIM.http;

import com.bubbleIM.http.resources.RootResource;
import com.bubbleIM.http.resources.UserResource;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.CompletionHandler;
import org.glassfish.grizzly.GrizzlyFuture;
import org.glassfish.grizzly.filterchain.Filter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

  private final String GRIZZLY = "grizzly";
  private HttpServer httpServer;

  public Server() {
    URI baseUri = UriBuilder.fromUri("http://localhost/").port(8081).build();
    ResourceConfig resourceConfig = new ResourceConfig(RootResource.class, UserResource.class);
    httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig, false);

    NetworkListener websocketListener = new NetworkListener("websocket", "localhost", 8082);
//    Iterator it = websocketListener.getFilterChain().iterator();
//    while(it.hasNext()) {
//      Filter f = (Filter)it.next();
//      System.out.println(f.);
//    }

    httpServer.addListener(websocketListener);


  }

  public void initWebSockets(WebSocketApplication websocketApplication) {
    WebSocketAddOn webSocketAddOn = new WebSocketAddOn();
    httpServer.getListener("websocket").registerAddOn(webSocketAddOn);
    WebSocketEngine.getEngine().register("", "/chat", websocketApplication);
  }

  public void startUp() {
    try {
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void shutdown() {
    if (httpServer.isStarted()) {
      GrizzlyFuture shutdownResult = httpServer.shutdown();
      shutdownResult.addCompletionHandler(new CompletionHandler() {
        @Override
        public void cancelled() {
          System.out.println("Shutdown is cancelled");
        }

        @Override
        public void failed(Throwable throwable) {
          System.out.println("Shutdown is failed with exception" + throwable);
        }

        @Override
        public void completed(Object result) {
          System.out.println("Shutdown completed with result" + result);
        }

        @Override
        public void updated(Object result) {
          System.out.println("Shutdown status is updated" + result);
        }
      });
    }
  }
}
