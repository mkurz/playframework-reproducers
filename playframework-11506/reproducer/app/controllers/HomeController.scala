package controllers

import javax.inject._
import play.api._
import play.api.cache.Cached
import play.api.libs.ws.WSClient
import play.api.mvc._
import scala.concurrent.duration._

@Singleton
class HomeController @Inject()
(cached                            : Cached,
 ws                                : WSClient,
 override val controllerComponents : ControllerComponents,
) extends AbstractController(controllerComponents) with Logging {

  import org.owasp.html.Sanitizers

  val policy = Sanitizers.FORMATTING
                         .and(Sanitizers.LINKS)
                         .and(Sanitizers.TABLES)
                         .and(Sanitizers.BLOCKS)
                         .and(Sanitizers.IMAGES)
                         .and(Sanitizers.STYLES)

  def websiteSanitized(address : String)  = cached((reqH: RequestHeader) => f"website-sanitized-${reqH.uri}", duration = 90.seconds) {
    Action.async { implicit request : Request [AnyContent] =>
      //_controllerNumber += 1
      //logger.info(f"> ${_controllerNumber} controller invocation")
      //println(f"> ${_controllerNumber} controller invocation")

      implicit val ec = controllerComponents.executionContext;
      val ok = for {
        response <- {
          //_requestNumber += 1
          //logger.info(f"> ${_requestNumber} Running HTTP request")
          //println(f"> ${_requestNumber} Running HTTP request")

          val upstreamRequest  = ws.url(address).withRequestTimeout(10000.millis)
          upstreamRequest.get()
        }
        result = policy.sanitize(response.body)
      } yield {
        Ok(result)
      }

      ok
    }
  }
}
