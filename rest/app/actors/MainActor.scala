package actors

import akka.actor.{Props, ActorLogging, Actor}

object MainActor {
  def props = Props[MainActor]
}

class MainActor extends Actor with ActorLogging {

  def receive = {

    case msg =>
      log.info(s"received: $msg")
  }
}
