package com.sciforma.lab.graphql.config;

import javax.inject.Singleton;

import com.sciforma.lab.graphql.modules.ModulesPackageBinder;
import graphql.GraphQL;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {

  public JerseyApplication () {
    registerClasses (GraphQLEndpoint.class, CorsFilter.class);
    register (new AbstractBinder () {

      @Override
      protected void configure () {
        bindFactory (GraphQLFactory.class).to (GraphQL.class).in (Singleton.class);
        install (new ModulesPackageBinder ());
      }
    });
  }
}
