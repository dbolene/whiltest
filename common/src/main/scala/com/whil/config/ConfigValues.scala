package com.whil.config

/**
  *
  */
object ConfigValues {

  lazy val config = Configuration.config

  lazy val testValue: String = Configuration.config.getString("twitter.test.value")

}
