package com.sciforma.lab.graphql.config;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.glassfish.jersey.internal.inject.InjectionManager;

public class DataFetcherDelegate<T, D extends DataFetcher<T>> implements DataFetcher<T> {

  private final InjectionManager injectionManager;

  private final Class<D> delegateClass;

  public DataFetcherDelegate (InjectionManager injectionManager, Class<D> delegateClass) {
    this.injectionManager = injectionManager;
    this.delegateClass = delegateClass;
  }

  @Override
  public T get (DataFetchingEnvironment environment) throws Exception {
    return injectionManager.getInstance (delegateClass).get (environment);
  }
}
