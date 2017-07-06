package controllers

import model.Sensor._
import model.{Device, Position, Sensor}
import play.api.libs.json._
import play.api.mvc._


class DevicesController(cc: ControllerComponents) extends AbstractController(cc) {

  def getDevices() = Action {
    val pos = Position(1, 1, 0)
    val sensLuz = Sensor("sensorId1", "Luz")
    val sensHum = Sensor("sensorId2", "Hum")
    val device = Device("deviceId", "house1", pos, List(sensLuz, sensHum))

    Ok(Json.toJson(device)).as(JSON)
  }
}
