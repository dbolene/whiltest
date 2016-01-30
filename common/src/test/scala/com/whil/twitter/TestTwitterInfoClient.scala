package com.whil.twitter

import org.junit.Assert._
import org.junit.Test
import org.scalatest.junit.JUnitSuite

/**
  *
  */
class TestTwitterInfoClient extends JUnitSuite {

  @Test def testConfigValueAccess() {

    assertTrue("testing", true)
    TwitterInfoClient.fetchInfo("dyross")
  }
}
