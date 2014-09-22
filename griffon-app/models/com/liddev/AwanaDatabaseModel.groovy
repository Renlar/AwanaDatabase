package com.liddev

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class AwanaDatabaseModel {
    @FXObservable String clickCount = "0"
}