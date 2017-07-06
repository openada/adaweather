package controllers

import model.{Device, Position, Sensor}
import play.api.libs.json._
import play.api.mvc._
import model.Sensor._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
class HomeController(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def test() = Action {
    val pos = Position(1, 1, 0)
    val sensLuz = Sensor("sensorId1", "Luz")
    val sensHum = Sensor("sensorId2", "Hum")
    val device = Device("sensorId", "house1", pos, List(sensLuz, sensHum))
    
    Ok(Json.toJson(device)).as(JSON)
  }
}
