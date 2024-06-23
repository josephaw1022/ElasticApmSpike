package org.acme.Utilities.Mediator;

public interface CommandHandler<C extends Command<R>, R> {
    R handle(C command);
}
