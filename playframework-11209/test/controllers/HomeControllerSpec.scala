package controllers

import org.scalatest.TestData
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Application
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneServerPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      System.out.println(port) // "debugging" ;)
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }
  }
}
