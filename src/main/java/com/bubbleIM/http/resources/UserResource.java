package com.bubbleIM.http.resources;

import com.google.common.base.Strings;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.http.HttpRequestPacket.Builder;

@Path("/api/users")
public class UserResource {


  @GET
  @Path("/login")
  @Produces("text/html")
  public static final Response login(@QueryParam("u") String username, @QueryParam("p") String password) {
    if(Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
      return Response.status(Status.BAD_REQUEST).entity("Invalid Login request").build();
    }

    //Verify if user is valid
    //return 401 unauthorized if user invalid.
    //if user valid return the Message html
    return Response.status(Status.OK).build();
  }

  @POST
  @Path("/signup")
  @Produces("text/html")
  public static final Response signup(@QueryParam("u") String username, @QueryParam("p") String password) {
    if(Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
      return Response.status(Status.BAD_REQUEST).entity("Invalid signup request").build();
    }

    //Register user
    //if user valid return the Message html
    return Response.status(Status.CREATED).build();
  }
}
