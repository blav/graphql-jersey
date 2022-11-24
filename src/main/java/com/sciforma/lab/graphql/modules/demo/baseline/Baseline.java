package com.sciforma.lab.graphql.modules.demo.baseline;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Baseline {

  private List<Slot> slots;

}
