package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    val checkboxCount = request.getQueryString("checkboxCount").map(_.toInt).getOrElse(10000)
    Ok(views.html.index(checkboxCount))
  }

  def testForPost() = Action(parse.multipartFormData) { implicit request: Request[MultipartFormData[play.api.libs.Files.TemporaryFile]] =>
    Redirect(controllers.routes.HomeController.index())
  }
}
