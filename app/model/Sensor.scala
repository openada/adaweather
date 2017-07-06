package model

import play.api.libs.json.Json

case class MetricValue(id: String, value: Double, timestamp: Long)

case class Sensor(id: String, name: String)

case class Device(id: String, name: String, position: Position, sensors: List[Sensor])

object Sensor {
  implicit val metricValueWriter = Json.writes[MetricValue]
  implicit val sensorWriter = Json.writes[Sensor]
  implicit val deviceWriter = Json.writes[Device]
}
        