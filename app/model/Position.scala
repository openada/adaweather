package model

import play.api.libs.json._


case class Position(lat: Double, long: Double, alt: Double) {}

object Position{
  implicit val positionWriter = Json.writes[Position]
}
