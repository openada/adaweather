package controllers

import model.Device
import play.api.libs.json._
import play.api.mvc._
import services.DataSource

import scala.collection.immutable.Iterable
import scala.concurrent.ExecutionContext.Implicits.global

class DevicesController(cc: ControllerComponents, ds: DataSource) extends AbstractController(cc) {

  def getDevicesAsJson: Action[AnyContent] =
    result(devices => Ok(Json.toJson(devices)).as(JSON))

  def getDevicesAsHTML: Action[AnyContent] =
    result(devices => Ok(views.html.devices(devices.toSeq)))

  private[this] def result(mapping: Iterable[Device] => Result) = Action.async {
    ds.devices.map { devices =>
      mapping(devices)
    }
  }
}
