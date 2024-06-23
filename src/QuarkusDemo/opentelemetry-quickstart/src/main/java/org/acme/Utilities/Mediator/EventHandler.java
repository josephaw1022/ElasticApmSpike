package org.acme.Utilities.Mediator;

public interface EventHandler<E extends Event> {
    void handle(E event);
}
