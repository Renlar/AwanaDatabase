package com.liddev

import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable

/**
 * @author Renlar <liddev.com>
 */
@ArtifactProviderFor(GriffonModel)
class AwanaDatabaseModel {

  @FXObservable
  String clickCount = "0"
}