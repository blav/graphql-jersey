package com.sciforma.lab.graphql.modules.core;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import com.sciforma.lab.graphql.config.ModuleComponents;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

@Service
@Priority (0)
@ModuleComponents (coercions = InstantCoercion.class, schemaFragments = "core.graphqls")
public class CoreGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_QUERY = "Query";

  public static final String TYPE_INSTANT = "Instant";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, Builder runtimeWiring) {
    runtimeWiring.scalar (GraphQLScalarType.newScalar ()
      .name (TYPE_INSTANT)
      .coercing (newCoercingDelegate (InstantCoercion.class))
      .build ());
  }
}
