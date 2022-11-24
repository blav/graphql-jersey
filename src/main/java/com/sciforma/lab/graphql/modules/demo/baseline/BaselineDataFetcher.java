package com.sciforma.lab.graphql.modules.demo.baseline;

import com.sciforma.lab.graphql.modules.demo.task.Task;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class BaselineDataFetcher implements DataFetcher<Baseline> {

  @Override
  public Baseline get (DataFetchingEnvironment environment) {
    Task task = environment.getSource ();
    return task.getBaseline ();
  }
}
