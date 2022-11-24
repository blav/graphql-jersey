package com.sciforma.lab.graphql.modules.demo.baseline;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Slot {

  private Long start;

  private Long finish;

}
