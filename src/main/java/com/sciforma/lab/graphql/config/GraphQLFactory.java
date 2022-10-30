package com.sciforma.lab.graphql.config;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.StreamSupport;

import graphql.GraphQL;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;

@Service
@Singleton
public class GraphQLFactory implements Factory<GraphQL> {

  @Inject
  private IterableProvider<GraphQLModule> modules;

  @Override
  public GraphQL provide () {
    TypeDefinitionRegistry registry = new TypeDefinitionRegistry ();
    Builder runtimeWiringBuilder = newRuntimeWiring ();
    GraphQLCodeRegistry.Builder codeRegistry = GraphQLCodeRegistry.newCodeRegistry ();
    runtimeWiringBuilder.codeRegistry (codeRegistry);
    StreamSupport.stream (modules.spliterator (), false)
      .sorted (comparing (provider -> ofNullable (provider.getClass ().getAnnotation (Priority.class))
        .map (Priority::value)
        .orElse (0)))
      .forEach (provider -> provider.provideTypes (registry, runtimeWiringBuilder));

    return GraphQL.newGraphQL (new SchemaGenerator ()
      .makeExecutableSchema (registry, runtimeWiringBuilder.build ())).build ();
  }

  @Override
  public void dispose (GraphQL graphQL) {
  }
}
