package model

import play.api.libs.json.{Json, OWrites}


case class MetricValue(id: String, value: Double, timestamp: Long)

object MetricValue {
  implicit val sensorWriter: OWrites[Sensor] = Json.writes[Sensor]
}
