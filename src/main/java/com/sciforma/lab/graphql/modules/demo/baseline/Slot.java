package com.sciforma.lab.graphql.modules.demo.baseline;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Slot {

  private Instant start;

  private Instant finish;

}
