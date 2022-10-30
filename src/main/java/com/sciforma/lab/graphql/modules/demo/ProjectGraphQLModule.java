package com.sciforma.lab.graphql.modules.demo;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import com.sciforma.lab.graphql.config.ModuleComponents;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

import static com.sciforma.lab.graphql.modules.core.QueryGraphQLModule.TYPE_QUERY;
import static graphql.language.FieldDefinition.newFieldDefinition;
import static graphql.language.ListType.newListType;
import static graphql.language.NonNullType.newNonNullType;
import static graphql.language.ObjectTypeDefinition.newObjectTypeDefinition;
import static graphql.language.ScalarTypeDefinition.newScalarTypeDefinition;
import static graphql.language.TypeName.newTypeName;

@Service
@Priority (10)
@ModuleComponents (dataFetchers = ProjectsDataFetcher.class, coercings = ProjectIdCoercing.class)
public class ProjectGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_PROJECT = "Project";

  public static final String TYPE_PROJECT_ID = "ProjectId";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, RuntimeWiring.Builder runtimeWiring) {
    registry.add (newScalarTypeDefinition ()
      .name (TYPE_PROJECT_ID)
      .build ());

    registry.add (newObjectTypeDefinition ()
      .name (TYPE_PROJECT)
      .fieldDefinition (newFieldDefinition ()
        .name ("id")
        .type (newNonNullType (newTypeName (TYPE_PROJECT_ID).build ()).build ())
        .build ())
      .build ());

    transform (registry, TYPE_QUERY, builder -> builder
      .fieldDefinition (newFieldDefinition ()
        .name ("projects")
        .type (newListType (newNonNullType (newTypeName (TYPE_PROJECT).build ()).build ()).build ())
        .build ()));

    runtimeWiring
      .type (TYPE_QUERY, typeWiring -> typeWiring
        .dataFetcher ("projects", newDataFetcherDelegate (ProjectsDataFetcher.class)))
      .scalar (GraphQLScalarType.newScalar ()
        .name (TYPE_PROJECT_ID)
        .coercing (newCoercingDelegate (ProjectIdCoercing.class))
        .build ())
    ;
  }
}
