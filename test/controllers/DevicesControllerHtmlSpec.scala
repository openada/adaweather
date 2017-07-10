package controllers

import boot.Boot
import mocks.DataSourceMock
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._
import play.api.{ApplicationLoader, Environment}

class DevicesControllerHtmlSpec extends PlaySpec with DataSourceMock {

  "DevicesController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new DevicesController(stubControllerComponents(), dataSourceMock)
      val home = controller.getDevicesAsHTML.apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Ada weather")
    }

    "render the index page from the router" in {
      val context = ApplicationLoader.createContext(Environment.simple())
      val app = new Boot().load(context)
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Ada weather")
    }
  }
}
