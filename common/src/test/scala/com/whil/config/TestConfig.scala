package com.whil.config

import org.junit.Assert._
import org.junit.Test
import org.scalatest.junit.JUnitSuite

/**
  *
  */
class TestConfig extends JUnitSuite {

  @Test def testConfigValueAccess() {

    // from common main/resources/application.conf
    assertTrue(ConfigValues.testValue == "aValue")
  }
}
