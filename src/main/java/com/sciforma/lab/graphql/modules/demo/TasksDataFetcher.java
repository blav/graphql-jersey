package com.sciforma.lab.graphql.modules.demo;

import java.util.Collections;
import java.util.List;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class TasksDataFetcher implements DataFetcher<List<Task>> {

  @Override
  public List<Task> get (DataFetchingEnvironment environment) {
    Project project = environment.getSource ();
    return Collections.emptyList ();
  }
}
