package controllers

import model.Sensor._
import model.{Device, Position, Sensor}
import play.api.libs.json._
import play.api.mvc._


class DevicesController(cc: ControllerComponents) extends AbstractController(cc) {

  def getDevicesJson = Action {
    Ok(Json.toJson(getDevices)).as(JSON)
  }

  def getDevicesHtml = Action {
    Ok(views.html.devices(getDevices))
  }

  def getDevices: List[Device] = {
    List(
      Device(
        "001",
        "CaDani",
        Position(51.461999, 0.125753, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      ),
      Device(
        "002",
        "Adri house",
        Position(51.462485, 0.126115, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      ),
      Device(
        "002",
        "Angelote pelote",
        Position(40.733362, -3.946101, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      )
    )
  }
}
