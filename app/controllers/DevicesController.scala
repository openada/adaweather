package controllers

import model.Sensor._
import play.api.libs.json._
import play.api.mvc._
import services.DataSource


class DevicesController(cc: ControllerComponents, ds: DataSource) extends AbstractController(cc) {

  def getDevices() = Action {
    val devices = ds.devices

    Ok(Json.toJson(devices)).as(JSON)
  }
}
