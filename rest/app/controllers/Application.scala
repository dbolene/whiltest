package controllers

import actors.TwitterWordCloudServiceActor
import akka.actor.ActorSystem
import play.api.libs.json.{Reads, JsPath}
import play.api.mvc._
import protocol.TwitterWordCloudServiceActorProtocol._

class Application extends Controller {

  import play.api.libs.functional.syntax._

//  implicit val fetchCloudKeywordsReads: Reads[FetchCloudKeywords] = (
//      (JsPath \ "twitterHandles").read[List[String]]
//  ) (FetchCloudKeywords.apply _)

  implicit val fetchCloudKeywordsReads: Reads[FetchCloudKeywords] =
    (JsPath \ "twitterHandles").read(
      Reads.list[String]
    ).map( handles => FetchCloudKeywords(handles))

  val system = ActorSystem("WhilSystem")

  val mainActor = system.actorOf(TwitterWordCloudServiceActor.props, TwitterWordCloudServiceActor.name)

  mainActor ! Initialize

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def fetchCloudKeywords() = Action(parse.json) { request =>
    val fetchRequestJson = request.body
    val fetchRequest = fetchRequestJson.as[FetchCloudKeywords]

    mainActor ! fetchRequest

    Ok("okay")

  }
}
