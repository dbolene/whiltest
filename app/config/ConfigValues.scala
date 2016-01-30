package config

import play.api.Play

/**
  *
  */
object ConfigValues {

  lazy val twitterConsumerKey = Play.current.configuration.getString("twitter.consumer.key").orNull
  lazy val twitterConsumerSecret = Play.current.configuration.getString("twitter.consumer.secret").orNull
  lazy val twitterAccessToken = Play.current.configuration.getString("twitter.accesstoken").orNull
  lazy val twitterAccessTokenSecret = Play.current.configuration.getString("twitter.accesstokensecret").orNull

}
