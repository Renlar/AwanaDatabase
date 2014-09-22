package com.liddev

import griffon.core.test.GriffonUnitRule
import griffon.core.test.TestFor
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.fail

/**
 * @author Renlar <liddev.com>
 */
@TestFor(AwanaDatabaseController)
class AwanaDatabaseControllerTest {

  static {
    // force initialization JavaFX Toolkit
    new javafx.embed.swing.JFXPanel()
  }

  private AwanaDatabaseController controller

  @Rule
  public final GriffonUnitRule griffon = new GriffonUnitRule()

  @Test
  void testClickAction() {
    fail('Not yet implemented!')
  }
}