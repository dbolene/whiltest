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

    case msg: FetchCloudKeywords =>
      log.info(s"received -> FetchCloudKeywords: $msg")

    case msg =>
      log.info(s"received -> $msg")
  }
}
