package com.sciforma.lab.graphql.modules.demo;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import com.sciforma.lab.graphql.config.ModuleComponents;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

import static com.sciforma.lab.graphql.modules.demo.ProjectGraphQLModule.TYPE_PROJECT;
import static graphql.language.FieldDefinition.newFieldDefinition;
import static graphql.language.ListType.newListType;
import static graphql.language.NonNullType.newNonNullType;
import static graphql.language.ObjectTypeDefinition.newObjectTypeDefinition;
import static graphql.language.TypeName.newTypeName;

@Service
@Priority (20)
@ModuleComponents (dataFetchers = TasksDataFetcher.class)
public class TaskGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_TASK = "Task";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, Builder runtimeWiring) {
    registry.add (newObjectTypeDefinition ()
      .name (TYPE_TASK)
      .fieldDefinition (newFieldDefinition ()
        .name ("id")
        .type (newNonNullType (newTypeName ("ID").build ()).build ())
        .build ())
      .build ());

    transform (registry, TYPE_PROJECT, builder -> builder
      .fieldDefinition (newFieldDefinition ()
        .name ("tasks")
        .type (newListType (newNonNullType (newTypeName (TYPE_TASK).build ()).build ()).build ())
        .build ()));

    runtimeWiring.type (TYPE_PROJECT, typeWiring -> typeWiring.dataFetcher ("tasks",
      newDataFetcherDelegate (TasksDataFetcher.class)));
  }
}
