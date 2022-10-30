package com.sciforma.lab.graphql.config;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Contract;

import static java.util.Arrays.stream;

@Contract
public interface GraphQLModule {

  void provideTypes (TypeDefinitionRegistry registry, RuntimeWiring.Builder runtimeWiring);

  static <M extends GraphQLModule> Binder newModuleBinder (@NotNull Class<M> moduleClass) {
    return new AbstractBinder () {
      @Override
      protected void configure () {
        bind (moduleClass).to (GraphQLModule.class).in (Singleton.class);
        Optional.of (moduleClass)
          .map (c -> c.getAnnotation (ModuleComponents.class))
          .map (ModuleComponents::dataFetchers)
          .ifPresent (fetchers -> stream (fetchers)
            .forEach (fetcher -> bindAsContract (fetcher).in (RequestScoped.class)));

        Optional.of (moduleClass)
          .map (c -> c.getAnnotation (ModuleComponents.class))
          .map (ModuleComponents::coercings)
          .ifPresent (coercings -> stream (coercings)
            .forEach (coercing -> bindAsContract (coercing).in (RequestScoped.class)));
      }
    };
  }
}
