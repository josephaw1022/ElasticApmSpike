package org.acme.Example.Commands;

import org.acme.Example.DTO.ExampleResponse;
import org.acme.Utilities.Mediator.Command;

public class CreateExample implements Command<ExampleResponse> {

    public String name = null;
    public String description = null;
}
