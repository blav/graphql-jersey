package com.sciforma.lab.graphql.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import graphql.schema.Coercing;
import graphql.schema.DataFetcher;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention (RUNTIME)
@Target (TYPE)
public @interface ModuleComponents {

  Class<? extends DataFetcher<?>>[] dataFetchers () default {};

  Class<? extends Coercing<?, ?>>[] coercions () default {};

  String[] schemaFragments () default {};

}
