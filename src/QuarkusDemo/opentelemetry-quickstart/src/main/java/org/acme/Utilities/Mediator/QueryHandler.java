package  org.acme.Utilities.Mediator;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
