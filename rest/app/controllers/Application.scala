package controllers

import actors.MainActor
import akka.actor.ActorSystem
import play.api._
import play.api.mvc._

class Application extends Controller {

  val system = ActorSystem("WhilSystem")

  val mainActor = system.actorOf(MainActor.props)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
