package clients

import config.ConfigValues
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

/**
  *
  */
object TwitterInfoClient {

  def fetchInfo(userId: String): Either[String,String] = {
    val consumer = new CommonsHttpOAuthConsumer(ConfigValues.twitterConsumerKey, ConfigValues.twitterConsumerSecret)
    consumer.setTokenWithSecret(ConfigValues.twitterAccessToken, ConfigValues.twitterAccessTokenSecret)

    val request = new HttpGet(s"https://api.twitter.com/1.1/users/show.json?screen_name=$userId")
    consumer.sign(request)
    val client = new DefaultHttpClient()
    val response = client.execute(request)

    println(response.getStatusLine.getStatusCode)

    response.getStatusLine.getStatusCode match {
      case 200 =>
        val responseString = IOUtils.toString(response.getEntity.getContent)
        println(responseString)
        Right(responseString)
      case 400 =>
        Left("Not Found")
    }
  }
}
