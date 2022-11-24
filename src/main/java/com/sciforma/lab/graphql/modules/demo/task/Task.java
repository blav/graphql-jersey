package com.sciforma.lab.graphql.modules.demo.task;

import java.util.List;

import com.sciforma.lab.graphql.modules.demo.baseline.Baseline;
import com.sciforma.lab.graphql.modules.demo.baseline.Slot;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Task {

  private String id;

  private Baseline baseline;

  private List<Slot> slots;

}
