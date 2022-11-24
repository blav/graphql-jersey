package com.sciforma.lab.graphql.config;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import graphql.ExecutionInput.Builder;
import graphql.GraphQL;

import static graphql.ExecutionInput.newExecutionInput;
import static java.util.Optional.ofNullable;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path ("/")
public class GraphQLEndpoint {

  @Inject
  private GraphQL graph;

  @POST
  @Path ("/graph")
  @Produces (APPLICATION_JSON + ";charset=utf-8")
  public Response query (GrahphQLRequestPayload payload) {
    Builder query = newExecutionInput ().query (payload.getQuery ());
    ofNullable (payload.getVariables ())
      .ifPresent (query::variables);

    ofNullable (payload.getOperationName ())
      .ifPresent (query::operationName);

    return Response
      .ok (graph.execute (query.build ()).getData ())
      .build ();
  }
}
