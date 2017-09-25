package controllers

import dal.DeviceRepository
import model.Device
import model.request.NewDevice
import play.api.libs.json._
import play.api.mvc._
import services.DataSource
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.i18n.I18nSupport

import scala.collection.immutable.Iterable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DevicesController(cc: ControllerComponents, ds: DataSource) extends AbstractController(cc) with I18nSupport {

  val repo: DeviceRepository = ds.deviceRepository

  val newDeviceForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "deviceIds" -> list(longNumber)
    )(NewDevice.apply)(NewDevice.unapply)
  )

  def getDevicesAsJson: Action[AnyContent] = Action.async {
    result(devices => Ok(Json.toJson(devices)).as(JSON))
  }

  def getDevicesAsHTML: Action[AnyContent] = Action.async { implicit request =>
    devicesHtml(newDeviceForm, Ok)
  }

  private[this] def devicesHtml(deviceForm: Form[NewDevice], status: Status= Ok)(implicit req: Request[AnyContent]): Future[Result] = {
    repo.findAllSensors flatMap { sensors =>
      result(devices => status(views.html.devices(devices.toSeq, sensors, deviceForm)))
    }
  }

  def addDevice: Action[AnyContent] = Action.async { implicit request =>
    newDeviceForm.bindFromRequest.fold(
      errors => devicesHtml(errors, BadRequest),
      newDevice => repo.addDevice(newDevice).map(_ => Redirect("/"))
    )
  }

  private[this] def result(mapping: Iterable[Device] => Result) =
    repo.findAll.map { devices =>
      mapping(devices)
    }

}
