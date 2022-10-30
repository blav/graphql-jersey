package com.sciforma.lab.graphql.config;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import graphql.GraphQL;

import static graphql.ExecutionInput.newExecutionInput;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path ("/")
public class GraphQLEndpoint {

  @Inject
  private GraphQL graph;

  @POST
  @Path ("/graph")
  @Produces (APPLICATION_JSON + ";charset=utf-8")
  public Response query (String query) {
    return Response
      .ok (graph.execute (newExecutionInput ().query (query).build ()).getData ())
      .build ();
  }
}
