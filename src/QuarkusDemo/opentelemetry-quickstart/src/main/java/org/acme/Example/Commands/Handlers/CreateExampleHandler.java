package org.acme.Example.Commands.Handlers;

import org.acme.Example.Commands.CreateExample;
import org.acme.Example.DTO.ExampleResponse;
import org.acme.Utilities.Mediator.CommandHandler;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CreateExampleHandler implements CommandHandler<CreateExample, ExampleResponse> {

    @Override
    public ExampleResponse handle(CreateExample command) {

        return new ExampleResponse(command.name, command.description);
    }
}
