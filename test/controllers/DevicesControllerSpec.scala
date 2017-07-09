package controllers

import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

class DevicesControllerSpec extends PlaySpec {

  "Devices Controller GET /devices" should {

    "show a mocked device" in {
      val controller = new DevicesController(stubControllerComponents())
      val devices = controller.getDevicesJson.apply(FakeRequest(GET, "/devices"))

      status(devices) mustBe OK
      contentType(devices) mustBe Some(JSON)
      contentAsString(devices) must include("\"id\":\"001\"")
    }
  }
}
