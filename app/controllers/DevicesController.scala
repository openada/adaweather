package controllers

import model.Sensor._
import play.api.libs.json._
import play.api.mvc._
import services.DataSource


class DevicesController(cc: ControllerComponents, ds: DataSource) extends AbstractController(cc) {

  def getDevices(extension: String) = Action {
    val devices = ds.devices

    extension match {
      case "json" => Ok(Json.toJson(devices)).as(JSON)
      case _ => Ok(views.html.devices(devices))
    }
  }
}
