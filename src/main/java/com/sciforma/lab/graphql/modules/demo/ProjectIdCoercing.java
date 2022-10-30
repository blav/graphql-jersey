package com.sciforma.lab.graphql.modules.demo;

import javax.validation.constraints.NotNull;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sciforma.lab.graphql.modules.demo.ProjectId.ProjectIdBuilder;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

public class ProjectIdCoercing implements Coercing<ProjectId, String> {

  private static final Pattern PROJECT_ID_PATTERN = Pattern.compile ("(\\d+):(\\d+)");

  @Override
  public String serialize (@NotNull Object dataFetcherResult) throws CoercingSerializeException {
    return Optional.of (dataFetcherResult)
      .filter (ProjectId.class::isInstance)
      .map (ProjectId.class::cast)
      .map (id -> id.getInternalId () + ":" + id.getVersion ())
      .orElseThrow (CoercingSerializeException::new);
  }

  @Override
  public @NotNull ProjectId parseValue (@NotNull Object input) throws CoercingParseValueException {
    return Optional.of (input)
      .filter (String.class::isInstance)
      .map (String.class::cast)
      .map (PROJECT_ID_PATTERN::matcher)
      .filter (Matcher::matches)
      .map (matcher -> new ProjectIdBuilder ()
        .internalId (Long.parseLong (matcher.group (1)))
        .version (Integer.parseInt (matcher.group (2)))
        .build ())
      .orElseThrow (CoercingParseLiteralException::new);
  }

  @Override
  public @NotNull ProjectId parseLiteral (@NotNull Object input) throws CoercingParseLiteralException {
    return parseValue (input);
  }
}
