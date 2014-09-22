package com.liddev

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

/**
 * @author Renlar <liddev.com>
 */

@ArtifactProviderFor(GriffonController)
class AwanaDatabaseController {

  AwanaDatabaseModel model

  @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
  void click() {
    int count = model.clickCount.toInteger()
    model.clickCount = String.valueOf(count + 1)
  }
}