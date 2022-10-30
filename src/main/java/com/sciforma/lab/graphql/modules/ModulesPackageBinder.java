package com.sciforma.lab.graphql.modules;

import com.sciforma.lab.graphql.modules.core.CorePackageBinder;
import com.sciforma.lab.graphql.modules.demo.DemoPackageBinder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ModulesPackageBinder extends AbstractBinder {

  @Override
  protected void configure () {
    install (new CorePackageBinder ());
    install (new DemoPackageBinder ());
  }
}
