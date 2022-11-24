package com.sciforma.lab.graphql.modules.demo;

import com.sciforma.lab.graphql.modules.demo.baseline.BaselineGraphQLModule;
import com.sciforma.lab.graphql.modules.demo.project.ProjectGraphQLModule;
import com.sciforma.lab.graphql.modules.demo.task.TaskGraphQLModule;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import static com.sciforma.lab.graphql.config.GraphQLModule.newModuleBinder;

public class DemoPackageBinder extends AbstractBinder {

  @Override
  protected void configure () {
    install (newModuleBinder (ProjectGraphQLModule.class));
    install (newModuleBinder (TaskGraphQLModule.class));
    install (newModuleBinder (BaselineGraphQLModule.class));
  }
}
