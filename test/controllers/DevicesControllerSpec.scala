package controllers

import mocks.DataSourceMock
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

class DevicesControllerSpec extends PlaySpec {

  "Devices Controller GET /devices" should {

    "show a mocked device" in {
      val controller = new DevicesController(stubControllerComponents(), DataSourceMock.mockDevices)
      val devices = controller.getDevicesAsJson.apply(FakeRequest(GET, "/devices"))

      status(devices) mustBe OK
      contentType(devices) mustBe Some(JSON)
      contentAsString(devices) must include("\"id\":\"001\"")
    }
  }
}
