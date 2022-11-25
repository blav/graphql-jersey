package com.sciforma.lab.graphql.modules.demo.project;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;
import java.util.List;

import com.sciforma.lab.graphql.modules.demo.baseline.Baseline;
import com.sciforma.lab.graphql.modules.demo.baseline.Slot;
import com.sciforma.lab.graphql.modules.demo.project.Project.ProjectBuilder;
import com.sciforma.lab.graphql.modules.demo.task.Task;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import static java.time.Instant.ofEpochSecond;
import static java.util.Collections.singletonList;

public class ProjectsDataFetcher implements DataFetcher<List<Project>> {

  @Context
  private UriInfo uriInfo;

  @Override
  public List<Project> get (DataFetchingEnvironment environment) {
    return Arrays.asList (
      new ProjectBuilder ()
        .id ("1")
        .name ("Project 1")
        .tasks (singletonList (Task.builder ()
          .id ("11")
          .slots (singletonList (new Slot (ofEpochSecond (1L), ofEpochSecond (2L))))
          .baseline (new Baseline (singletonList (new Slot (ofEpochSecond (3L), ofEpochSecond (4L)))))
          .build ()))
        .build (),
      new ProjectBuilder ()
        .id ("2")
        .name ("Project 2")
        .tasks (singletonList (Task.builder ().id ("21").build ()))
        .build ());
  }
}
