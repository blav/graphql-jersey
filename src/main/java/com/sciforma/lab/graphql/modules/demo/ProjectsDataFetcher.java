package com.sciforma.lab.graphql.modules.demo;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;
import java.util.List;

import com.sciforma.lab.graphql.modules.demo.Project.ProjectBuilder;
import com.sciforma.lab.graphql.modules.demo.ProjectId.ProjectIdBuilder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class ProjectsDataFetcher implements DataFetcher<List<Project>> {

  @Context
  private UriInfo uriInfo;

  @Override
  public List<Project> get (DataFetchingEnvironment environment) {
    return Arrays.asList (
      new ProjectBuilder ().id (new ProjectIdBuilder ().internalId (1).version (0).build ()).build (),
      new ProjectBuilder ().id (new ProjectIdBuilder ().internalId (2).version (0).build ()).build ());
  }
}
