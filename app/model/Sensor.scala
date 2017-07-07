package model

import play.api.libs.json.{Json, OWrites}

case class MetricValue(id: String, value: Double, timestamp: Long)

case class Sensor(id: String, name: String)

case class Device(id: String, name: String, position: Position, sensors: List[Sensor])

object Sensor {
  implicit val metricValueWriter: OWrites[MetricValue] = Json.writes[MetricValue]
  implicit val sensorWriter: OWrites[Sensor] = Json.writes[Sensor]
  implicit val deviceWriter: OWrites[Device] = Json.writes[Device]
}
        