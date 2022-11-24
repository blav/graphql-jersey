package com.sciforma.lab.graphql.modules.demo.baseline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Slot {

  private Long start;

  private Long finish;

}
