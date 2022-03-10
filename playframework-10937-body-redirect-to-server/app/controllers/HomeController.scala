package controllers

import akka.util.ByteString

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    println("Headers:")
    println("--------")
    request.headers.headers.foreach(header => println(s"${header._1}: ${header._2}"))
    println()
    println("Body size: " + request.body.asRaw.size)
    println()
    println("Body:")
    println("-----")
    println(request.body.asRaw.flatMap(_.asBytes()).getOrElse(ByteString.emptyByteString).utf8String)
    println()
    Ok("hello world!")
  }
}
