package model

import model.db.DeviceDb
import play.api.libs.json.{Json, OWrites}

case class Device(id: Long, name: String, position: Position, sensors: List[Sensor])

object Device {
  def apply(deviceDb: DeviceDb, sensors: List[Sensor]): Device =
    Device(deviceDb.id, deviceDb.name, deviceDb.position, sensors)

  implicit val deviceWriter: OWrites[Device] = Json.writes[Device]
}
