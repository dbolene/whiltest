package actors

import akka.actor.{Actor, ActorLogging, Props}
import protocol.TwitterUserDescriptionWordFetchActorProtocol._
import clients.TwitterInfoClient

object TwitterUserDescriptionWordFetchActor {
  def props = Props[TwitterUserDescriptionWordFetchActor]
}

class TwitterUserDescriptionWordFetchActor extends Actor with ActorLogging {

  def receive = {

    case msg: FetchUserDescriptionWordsForUser =>
      log.info(s"received -> FetchUserDescriptionWordsForUser: $msg")

      TwitterInfoClient.fetchInfo(msg.userId).fold(
        error => {
          sender() !
        },
        ok => {

        }
      )

    case msg =>
      log.info(s"received -> $msg")
  }

}
