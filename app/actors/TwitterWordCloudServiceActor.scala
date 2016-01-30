package actors

import akka.actor.{Props, ActorLogging, Actor}
import protocol.TwitterUserDescriptionWordFetchActorProtocol._
import protocol.TwitterWordCloudServiceActorProtocol._

object TwitterWordCloudServiceActor {
  def props = Props[TwitterWordCloudServiceActor]

  val name = "TwitterWordCloudServiceActor"
}

class TwitterWordCloudServiceActor extends Actor with ActorLogging {

  var data: TwitterWordCloudData = TwitterWordCloudData()

  def update(updateData: TwitterWordCloudData): Unit = {
    data = TwitterWordCloudData(
      fetchedUsers = updateData.fetchedUsers orElse data.fetchedUsers,
      wordCloud = updateData.wordCloud orElse data.wordCloud
    )
  }

  def receive = {

    case Initialize =>
      log.info(s"received -> Initialize")

    case msg: FetchCloudKeywordsForUsers =>
      log.info(s"received -> FetchCloudKeywords: $msg")
      msg.users.foreach(
        user => {
          if(!(data.fetchedUsers.isDefined && data.fetchedUsers.get.contains(user))) {
            val existingUsers: Set[String] = data.fetchedUsers getOrElse Set.empty[String]
            update(TwitterWordCloudData(fetchedUsers = Some(existingUsers + user)))
            val worker = context.actorOf(TwitterUserDescriptionWordFetchActor.props)
            worker ! FetchUserDescriptionWordsForUser(userId = user)
          }
        }
      )

    case msg: FetchedUserDescriptionWordsForUser =>
      log.info(s"received -> FetchCloudKeywords: $msg")
      var updateWordCloud = data.wordCloud.getOrElse(Map.empty[String,Int])

      msg.wordCount.foreach(tuple =>
        updateWordCloud = updateWordCloud + (tuple._1 -> (updateWordCloud.getOrElse(tuple._1, 0) + tuple._2))
      )

      update(TwitterWordCloudData(wordCloud = Some(updateWordCloud)))

      log.info(s"data: $data")

    case RequestWordCloud =>
      log.info(s"received -> RequestWordCloud")

      sender() ! WordCloud(wordCloud = data.wordCloud.getOrElse(Map.empty[String,Int]))

    case unknown =>
      log.warning(s"received -> $unknown")
  }
}

case class TwitterWordCloudData(
                               fetchedUsers: Option[Set[String]] = None,
                               wordCloud: Option[Map[String,Int]] = None
                               ) {
  override def toString = {
    "\nTwitterWordCloudData(\n" +
      s"  fetchedUsers: $fetchedUsers \n" +
      s"  wordCloud: $wordCloud \n" +
      ")\n"
  }
}


