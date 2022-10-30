package com.sciforma.lab.graphql.modules.core;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

import static graphql.language.ObjectTypeDefinition.newObjectTypeDefinition;

@Service
@Priority (0)
public class QueryGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_QUERY = "Query";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, Builder runtimeWiring) {
    registry.add (newObjectTypeDefinition ()
      .name ("Query")
      .build ());
  }
}
