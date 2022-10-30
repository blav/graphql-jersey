package com.sciforma.lab.graphql.config;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import graphql.language.ObjectTypeDefinition;
import graphql.schema.Coercing;
import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.jvnet.hk2.annotations.Service;

@Service
public abstract class AbstractGraphQLModule implements GraphQLModule {

  @Inject
  private InjectionManager injectionManager;

  protected <T, F extends DataFetcher<T>> DataFetcher<T> newDataFetcherDelegate (Class<F> dataFetcherClass) {
    return new DataFetcherDelegate<> (injectionManager, dataFetcherClass);
  }

  protected <I, O, C extends Coercing<I, O>> Coercing<I, O> newCoercingDelegate (Class<C> coercingClass) {
    return new CoercingDelegate<> (injectionManager, coercingClass);
  }

  protected void transform (TypeDefinitionRegistry registry, String typeName, Consumer<ObjectTypeDefinition.Builder> transformer) {
    ObjectTypeDefinition definition = registry.getType (typeName)
      .map (ObjectTypeDefinition.class::cast)
      .orElseThrow (NoSuchElementException::new);

    ObjectTypeDefinition transformed = definition.transform (transformer);
    registry.remove (definition);
    registry.add (transformed);
  }
}
