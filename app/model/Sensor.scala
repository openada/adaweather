package model

import play.api.libs.json.{Json, OWrites}


case class Sensor(id: String, name: String)

object Sensor {
  implicit val sensorWriter: OWrites[Sensor] = Json.writes[Sensor]
}
        