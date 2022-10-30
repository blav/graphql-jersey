package com.sciforma.lab.graphql.modules.demo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectId {

  private final long internalId;

  private final int version;

}
