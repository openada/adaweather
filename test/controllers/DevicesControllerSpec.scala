package controllers

import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class DevicesControllerSpec extends PlaySpec {

  "Devices Controller GET /devices" should {

    "show a mocked device" in {
      val controller = new DevicesController(stubControllerComponents())
      val devices = controller.getDevices.apply(FakeRequest(GET, "/devices"))

      status(devices) mustBe OK
      contentType(devices) mustBe Some(JSON)
      contentAsString(devices) must include("\"id\":\"deviceId\"")
    }
  }
}
