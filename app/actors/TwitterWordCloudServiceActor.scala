package actors

import akka.actor.{Props, ActorLogging, Actor}
import protocol.TwitterWordCloudServiceActorProtocol._

object TwitterWordCloudServiceActor {
  def props = Props[TwitterWordCloudServiceActor]

  val name = "TwitterWordCloudServiceActor"
}

class TwitterWordCloudServiceActor extends Actor with ActorLogging {

  def receive = {

    case Initialize =>
      log.info(s"received -> Initialize")

    case msg: FetchCloudKeywordsForUser =>
      log.info(s"received -> FetchCloudKeywords: $msg")

    case msg =>
      log.info(s"received -> $msg")
  }
}

case class TwitterWordCloudData(
                               fetchedUsers: Option[Set[String]] = None,
                               wordCloud: Option[Map[String,Int]] = None
                               )
