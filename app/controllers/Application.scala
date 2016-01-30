package controllers

import actors.TwitterWordCloudServiceActor
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._
import play.api.libs.json._
import play.api.mvc._
import protocol.TwitterWordCloudServiceActorProtocol._

import scala.util.{Success, Failure}

class Application extends Controller {

  import play.api.libs.functional.syntax._

  val system = ActorSystem("WhilSystem")

  val mainActor = system.actorOf(TwitterWordCloudServiceActor.props, TwitterWordCloudServiceActor.name)

  mainActor ! Initialize

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  implicit val fetchCloudKeywordsReads: Reads[FetchCloudKeywordsForUsers] =
    (JsPath \ "twitterHandles").read(
      Reads.list[String]
    ).map( handles => FetchCloudKeywordsForUsers(handles))

  def fetchCloudKeywords() = Action(parse.json) { request =>
    val fetchRequestJson = request.body
    val fetchRequest = fetchRequestJson.as[FetchCloudKeywordsForUsers]

    mainActor ! fetchRequest

    Ok("okay")
  }

  implicit object WordCloudWrites extends Writes[WordCloud] {
    def writes(p: WordCloud) = Json.obj(
      "wordcloud" -> Json.toJson(
        p.wordCloud.map( m =>
          Map("word" -> JsString(m._1), "count" -> Json.toJson(m._2))
        )
      )
  ) }

  def returnWordCloud() = Action { request =>
    implicit val timeout = Timeout(30 seconds)
      val future = mainActor ? RequestWordCloud
      val result = Await.result(future, timeout.duration)

      result match {
        case wordCloud: WordCloud =>
          System.out.println(s"\n\nwordCloud: $wordCloud")
          System.out.println(s"\n\nJson.toJson(wordCloud): ${Json.toJson(wordCloud)}")
          Ok(Json.toJson(wordCloud))
        case _ =>
          InternalServerError
      }
  }
}
