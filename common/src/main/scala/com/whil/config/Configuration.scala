package com.whil.config

import com.typesafe.config.ConfigFactory

/**
  *
  * @author dbolene
  */
object Configuration {

  lazy val config = ConfigFactory.load()


}
