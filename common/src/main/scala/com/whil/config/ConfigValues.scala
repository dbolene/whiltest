package com.whil.config

/**
  *
  */
object ConfigValues {

  lazy val config = Configuration.config

  lazy val testValue: String = Configuration.config.getString("twitter.test.value")

  lazy val twitterConsumerKey: String = Configuration.config.getString("twitter.consumer.key")
  lazy val twitterConsumerSecret: String = Configuration.config.getString("twitter.consumer.secret")
  lazy val twitterAccessToken: String = Configuration.config.getString("twitter.accesstoken")
  lazy val twitterAccessTokenSecret: String = Configuration.config.getString("twitter.accesstokensecret")

}
