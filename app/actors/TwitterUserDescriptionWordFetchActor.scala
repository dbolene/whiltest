package actors

import akka.actor.{PoisonPill, Actor, ActorLogging, Props}
import protocol.TwitterUserDescriptionWordFetchActorProtocol._
import clients.TwitterInfoClient
import play.api.libs.json._
object TwitterUserDescriptionWordFetchActor {
  def props = Props[TwitterUserDescriptionWordFetchActor]
}

class TwitterUserDescriptionWordFetchActor extends Actor with ActorLogging {

  def receive = {

    case msg: FetchUserDescriptionWordsForUser =>
      log.info(s"received -> FetchUserDescriptionWordsForUser: $msg")

      TwitterInfoClient.fetchInfo(msg.userId).fold(
        error => {
          log.error(error)
        },
        result => {
          val json: JsValue = Json.parse(result)
          val description = (json \ "description").as[String]
          var wordCounts = scala.collection.mutable.Map.empty[String,Int]
          description.split(" ").foreach(
            word => {
              wordCounts = wordCounts + (word -> (wordCounts.getOrElse(word, 0) + 1))
            }
          )
          sender() ! FetchedUserDescriptionWordsForUser(userId = msg.userId, wordCount = wordCounts.toMap)
        }
      )

      self ! PoisonPill

    case msg =>
      log.info(s"received -> $msg")
  }

}
