package com.sciforma.lab.graphql.modules.core;

import java.time.Instant;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import static java.util.Optional.ofNullable;

public class InstantCoercion implements Coercing<Instant, String> {

  @Override
  public String serialize (Object dataFetcherResult) throws CoercingSerializeException {
    return ofNullable (dataFetcherResult)
      .map (Instant.class::cast)
      .map (Instant::toString)
      .orElse (null);
  }

  @Override
  public Instant parseValue (Object input) throws CoercingParseValueException {
    return parseLiteral (input);
  }

  @Override
  public Instant parseLiteral (Object input) throws CoercingParseLiteralException {
    return ofNullable (input)
      .map (String.class::cast)
      .map (Instant::parse)
      .orElseThrow (IllegalStateException::new);
  }
}
