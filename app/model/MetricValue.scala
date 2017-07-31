package model

import play.api.libs.json.{Json, OWrites}


case class MetricValue(id: Long, deviceId: Long, sensorId: Long, value: Double, timestamp: Long)

object MetricValue {
  implicit val sensorWriter: OWrites[MetricValue] = Json.writes[MetricValue]
}
