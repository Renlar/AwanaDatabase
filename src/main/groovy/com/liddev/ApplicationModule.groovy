package com.liddev

import griffon.core.event.EventHandler
import griffon.core.injection.Module
import org.codehaus.griffon.runtime.core.injection.AbstractModule
import org.kordamp.jipsy.ServiceProviderFor

/**
 * @author Renlar <liddev.com>
 */
@ServiceProviderFor(Module)
class ApplicationModule extends AbstractModule {

  @Override
  protected void doConfigure() {
    bind(EventHandler)
        .to(ApplicationEventHandler)
        .asSingleton()
  }
}