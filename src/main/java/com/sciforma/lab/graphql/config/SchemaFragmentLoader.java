package com.sciforma.lab.graphql.config;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Singleton
public class SchemaFragmentLoader {

  public void merge (Class<? extends GraphQLModule> moduleClass, String schemaFile, SchemaParser parser, TypeDefinitionRegistry registry) {
    try (InputStream schema = moduleClass.getResourceAsStream (schemaFile)) {
      registry.merge (parser.parse (schema));
    } catch (IOException e) {
      throw new RuntimeException (e);
    }
  }
}
