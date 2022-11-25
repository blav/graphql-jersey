package com.sciforma.lab.graphql.modules.demo.baseline;

import javax.annotation.Priority;

import com.sciforma.lab.graphql.config.AbstractGraphQLModule;
import com.sciforma.lab.graphql.config.ModuleComponents;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.jvnet.hk2.annotations.Service;

import static com.sciforma.lab.graphql.modules.demo.task.TaskGraphQLModule.TYPE_TASK;
import static graphql.language.FieldDefinition.newFieldDefinition;
import static graphql.language.TypeName.newTypeName;

@Service
@Priority (30)
@ModuleComponents (dataFetchers = { BaselineDataFetcher.class }, schemaFragments = "baseline.graphqls")
public class BaselineGraphQLModule extends AbstractGraphQLModule {

  public static final String TYPE_BASELINE = "Baseline";

  public static final String TYPE_SLOT = "Slot";

  @Override
  public void provideTypes (TypeDefinitionRegistry registry, Builder runtimeWiring) {
    transform (registry, TYPE_TASK, builder -> builder
      .fieldDefinition (newFieldDefinition ()
        .name ("baseline")
        .type (newTypeName (TYPE_BASELINE).build ())
        .build ()));

    runtimeWiring.type (TYPE_TASK, typeWiring ->
      typeWiring.dataFetcher ("baseline", newDataFetcherDelegate (BaselineDataFetcher.class)));
  }
}
