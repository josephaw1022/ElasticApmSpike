package org.acme.Utilities.Mediator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class MediatorService {

    @Inject
    @Any
    Instance<CommandHandler<? extends Command<?>, ?>> commandHandlers;

    @Inject
    @Any
    Instance<EventHandler<? extends Event>> eventHandlers;

    @Inject
    @Any
    Instance<QueryHandler<? extends Query<?>, ?>> queryHandlers;

    @SuppressWarnings("unchecked")
    public <C extends Command<R>, R> R send(C command) {
        for (CommandHandler<?, ?> handler : commandHandlers) {
            if (handler.getClass().getName().contains("_ClientProxy")) {
                Class<?> superClass = handler.getClass().getSuperclass();

                for (Type genericType : superClass.getGenericInterfaces()) {
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;

                        Type[] typeArguments = parameterizedType.getActualTypeArguments();

                        if (typeArguments.length == 2) {

                            Class<?> commandType = (Class<?>) typeArguments[0];

                            if (commandType.equals(command.getClass())) {
                                return ((CommandHandler<C, R>) handler).handle(command);
                            }
                        }
                    }
                }
            }
        }

        throw new IllegalArgumentException("No handler found for command " + command.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void publish(E event) {
        for (EventHandler<?> handler : eventHandlers) {
            if (handler.getClass().getName().contains("_ClientProxy")) {
                Class<?> superClass = handler.getClass().getSuperclass();

                for (Type genericType : superClass.getGenericInterfaces()) {
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;

                        Type[] typeArguments = parameterizedType.getActualTypeArguments();

                        if (typeArguments.length == 1) {

                            Class<?> eventType = (Class<?>) typeArguments[0];

                            if (eventType.equals(event.getClass())) {
                                ((EventHandler<E>) handler).handle(event);
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <Q extends Query<R>, R> R query(Q query) {
        for (QueryHandler<?, ?> handler : queryHandlers) {
            if (handler.getClass().getName().contains("_ClientProxy")) {
                Class<?> superClass = handler.getClass().getSuperclass();

                for (Type genericType : superClass.getGenericInterfaces()) {
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;

                        Type[] typeArguments = parameterizedType.getActualTypeArguments();

                        if (typeArguments.length == 2) {

                            Class<?> queryType = (Class<?>) typeArguments[0];

                            if (queryType.equals(query.getClass())) {
                                return ((QueryHandler<Q, R>) handler).handle(query);
                            }
                        }
                    }
                }
            }
        }

        throw new IllegalArgumentException("No handler found for query " + query.getClass().getName());
    }

}
