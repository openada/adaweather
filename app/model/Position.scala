package model

import play.api.libs.json._


case class Position(latitude: Double, longitude: Double, altitude: Double)

object Position {
  implicit val positionWriter = Json.writes[Position]
}