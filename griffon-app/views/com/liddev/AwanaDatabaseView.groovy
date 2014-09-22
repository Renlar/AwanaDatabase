package com.liddev

import griffon.core.artifact.GriffonView
import griffon.metadata.ArtifactProviderFor

/**
 * @author Renlar <liddev.com>
 */
@ArtifactProviderFor(GriffonView)
class AwanaDatabaseView {

  FactoryBuilderSupport builder
  AwanaDatabaseModel model

  void initUI() {
    builder.application(title: application.configuration['application.title'],
                        sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
      scene(fill: WHITE, width: 200, height: 60) {
        gridPane {
          label(id: 'clickLabel', row: 0, column: 0,
                text: bind(model.clickCountProperty()))
          button(row: 1, column: 0, prefWidth: 200,
                 clickAction)
        }
      }
    }
  }
}