package com.sciforma.lab.graphql.config;

import javax.validation.constraints.NotNull;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.glassfish.jersey.internal.inject.InjectionManager;

public class CoercingDelegate<I, O, C extends Coercing<I, O>> implements Coercing<I, O> {

  private final InjectionManager injectionManager;

  private final Class<C> delegateClass;

  public CoercingDelegate (InjectionManager injectionManager, Class<C> delegateClass) {
    this.injectionManager = injectionManager;
    this.delegateClass = delegateClass;
  }

  @Override
  public @NotNull I parseLiteral (@NotNull Object input) throws CoercingParseLiteralException {
    return injectionManager.getInstance (delegateClass).parseLiteral (input);
  }

  @Override
  public O serialize (@NotNull Object dataFetcherResult) throws CoercingSerializeException {
    return injectionManager.getInstance (delegateClass).serialize (dataFetcherResult);
  }

  @Override
  public @NotNull I parseValue (@NotNull Object input) throws CoercingParseValueException {
    return injectionManager.getInstance (delegateClass).parseValue (input);
  }
}
