package com.sciforma.lab.graphql.config;

import java.util.Map;

public class GrahphQLRequestPayload {

  private String query;

  private Map<String, Object> variables;

  private String operationName;

  public String getQuery () {
    return query;
  }

  public Map<String, Object> getVariables () {
    return variables;
  }

  public String getOperationName () {
    return operationName;
  }
}
