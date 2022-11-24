package com.sciforma.lab.graphql.modules.demo.task;

import java.util.List;

import com.sciforma.lab.graphql.modules.demo.project.Project;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class TasksDataFetcher implements DataFetcher<List<Task>> {

  @Override
  public List<Task> get (DataFetchingEnvironment environment) {
    Project project = environment.getSource ();
    return project.getTasks ();
  }
}
