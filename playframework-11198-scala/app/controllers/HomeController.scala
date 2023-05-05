package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def user(user: User) = Action {
    Ok(s"${user.id} ${user.name} ${user.from} ${user.to}")
  }
  // #path

  // #query
//  def age(age: AgeRange) = Action {
//    Ok(s"From: ${age.from.toString} To: ${age.to.toString}")
//    }
  // #query

}
