package com.bubbleIM.http.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("")
public class RootResource {

  @GET
  @Produces("text/html")
  private static final Response getHomePage() {
     return null;
  }

}
