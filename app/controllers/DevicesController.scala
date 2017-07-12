package controllers

import model.Sensor._
import play.api.libs.json._
import play.api.mvc._
import services.DataSource


class DevicesController(cc: ControllerComponents, ds: DataSource) extends AbstractController(cc) {

  def getDevicesAsJson = Action {
    Ok(Json.toJson(ds.devices)).as(JSON)
  }

  def getDevicesAsHTML = Action {
    Ok(views.html.devices(ds.devices))
  }
}
