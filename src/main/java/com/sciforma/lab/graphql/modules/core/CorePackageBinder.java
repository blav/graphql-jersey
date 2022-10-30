package com.sciforma.lab.graphql.modules.core;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import static com.sciforma.lab.graphql.config.GraphQLModule.newModuleBinder;

public class CorePackageBinder extends AbstractBinder {

  @Override
  protected void configure () {
    install (newModuleBinder (QueryGraphQLModule.class));
  }
}
