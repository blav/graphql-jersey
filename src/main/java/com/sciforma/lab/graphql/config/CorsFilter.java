package com.sciforma.lab.graphql.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.function.BiConsumer;

import static javax.ws.rs.HttpMethod.OPTIONS;

@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

  public static final String PROCESSED = "CorsFilter.processed";

  @Override
  public void filter (ContainerRequestContext requestContext) {
    if (OPTIONS.equals (requestContext.getMethod ())) {
      ResponseBuilder builder = Response.ok ();
      addHeaders (requestContext, builder::header);
      requestContext.abortWith (builder.build ());
    }
  }

  @Override
  public void filter (ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    addHeaders (requestContext, responseContext.getHeaders ()::putSingle);
  }

  private void addHeaders (ContainerRequestContext requestContext, BiConsumer<String, String> consumer) {
    consumer.accept (
      "Access-Control-Allow-Origin", requestContext.getHeaderString ("Origin"));
    consumer.accept (
      "Access-Control-Allow-Credentials", "true");
    consumer.accept (
      "Access-Control-Allow-Headers",
      "origin, content-type, accept, authorization");
    consumer.accept (
      "Access-Control-Allow-Methods",
      "GET, POST, PUT, DELETE, OPTIONS, HEAD");
  }
}