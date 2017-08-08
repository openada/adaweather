package controllers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import mocks.DataSourceMock
import org.scalatest.OptionValues
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

class DevicesControllerSpec extends PlaySpec with OptionValues {

  implicit val system: ActorSystem = ActorSystem("test-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  "Devices Controller GET /devices" should {

    "show a mocked device" in {
      val controller = new DevicesController(stubControllerComponents(), DataSourceMock.mockDevices)
      val devices = controller.getDevicesAsJson.apply(FakeRequest(GET, "/devices"))

      status(devices) mustBe OK
      contentType(devices) mustBe Some(JSON)
      contentAsString(devices) must include("\"id\":1")
    }
  }

  "Add device request the addition to the repository" in {
    val controller = new DevicesController(stubControllerComponents(), DataSourceMock.mockDevices)
    val body = Json.parse("""{"name":"test-device"}""")
    val response = controller.addDevice.apply(
      FakeRequest(POST, "/devices")
        .withHeaders("ContentType" ->"application/json")
        .withJsonBody(body)
    )

    status(response) mustBe SEE_OTHER
    redirectLocation(response).value mustBe "/"
  }

  "Add device fails when the name is not provided" in {
    val controller = new DevicesController(stubControllerComponents(), DataSourceMock.mockDevices)
    val addDevice = controller.addDevice.apply(FakeRequest(POST, "/devices", FakeHeaders(), """{}"""))

    status(addDevice) mustBe BAD_REQUEST
  }
}
