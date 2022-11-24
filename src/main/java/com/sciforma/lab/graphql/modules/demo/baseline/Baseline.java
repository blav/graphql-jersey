package com.sciforma.lab.graphql.modules.demo.baseline;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Baseline {

  private List<Slot> slots;

}
