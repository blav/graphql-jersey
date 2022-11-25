package com.sciforma.lab.graphql.modules.demo.project;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import com.sciforma.lab.graphql.config.ModuleComponents;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

import static com.sciforma.lab.graphql.modules.core.CoreGraphQLModule.TYPE_QUERY;
import static graphql.language.FieldDefinition.newFieldDefinition;
import static graphql.language.ListType.newListType;
import static graphql.language.NonNullType.newNonNullType;
import static graphql.language.TypeName.newTypeName;

@Service
@Priority (10)
@ModuleComponents (dataFetchers = ProjectsDataFetcher.class, schemaFragments = "project.graphqls")
public class ProjectGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_PROJECT = "Project";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, RuntimeWiring.Builder runtimeWiring) {
    transform (registry, TYPE_QUERY, builder -> builder
      .fieldDefinition (newFieldDefinition ()
        .name ("projects")
        .type (newListType (newNonNullType (newTypeName (TYPE_PROJECT).build ()).build ()).build ())
        .build ()));

    runtimeWiring
      .type (TYPE_QUERY, typeWiring -> typeWiring
        .dataFetcher ("projects", newDataFetcherDelegate (ProjectsDataFetcher.class)))
    ;
  }
}
