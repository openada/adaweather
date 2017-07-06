package controllers

import boot.Boot
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._
import play.api.{ApplicationLoader, Environment}

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class DevicesControllerSpec extends PlaySpec {

  "Devices Controller GET" should {

    "show a mocked device" in {
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/devices"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }

    "render the index page from the router" in {
      val context = ApplicationLoader.createContext(Environment.simple())
      val app = new Boot().load(context)
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }
  }
}
