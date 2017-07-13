package model

import play.api.libs.json.{Json, OWrites}

case class Device(id: String, name: String, position: Position, sensors: List[Sensor])

object Device {
  implicit val deviceWriter: OWrites[Device] = Json.writes[Device]
}
