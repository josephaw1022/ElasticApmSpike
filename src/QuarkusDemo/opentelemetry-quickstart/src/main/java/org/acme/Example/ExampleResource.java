package org.acme.Example;

import org.acme.Example.Commands.CreateExample;
import org.acme.Example.DTO.ExampleResponse;
import org.acme.Utilities.Mediator.MediatorService;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/example")
public class ExampleResource {

    private final MediatorService mediatorService;

    public ExampleResource(MediatorService mediatorService) {
        this.mediatorService = mediatorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExampleResponse Hello () {
        Log.info("We are in the ExampleResource class!");

        var command = new CreateExample();
        command.name = "Example";
        command.description = "This is an example command";

        return mediatorService.send(command);
    }
}
