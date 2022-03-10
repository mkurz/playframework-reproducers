package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.streams._
import play.api.libs.ws._
import scala.concurrent.ExecutionContext
import akka.util.ByteString

@Singleton
class HomeController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents)(
  implicit ec: ExecutionContext
) extends BaseController {
  def forward(request: WSRequest): BodyParser[WSResponse] = BodyParser { req =>
    Accumulator.source[ByteString].mapFuture { source =>
      request
        .withBody(source)
        .execute("POST")
        .map(Right.apply)
    }
  }

  def index() = Action(forward(ws.url("http://localhost:9001"))) { req =>
    Ok("Uploaded")
  }
}