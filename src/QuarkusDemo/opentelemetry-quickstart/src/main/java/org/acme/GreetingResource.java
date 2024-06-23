package org.acme;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Log.info("Simple!");
        return "Hello from Quarkus REST";
    }

    @Path("/hello")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        Log.info("Simple!");
        return "Hello from Quarkus REST";
    }

}
