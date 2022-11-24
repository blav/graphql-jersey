package com.sciforma.lab.graphql.modules.demo.project;

import java.util.List;

import com.sciforma.lab.graphql.modules.demo.task.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Project {

  private String id;

  private List<Task> tasks;

}
