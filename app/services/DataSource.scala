package services

import model.{Device, Position, Sensor}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class DataSource(val dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {

  def devices = {
    val pos = Position(1, 1, 0)
    val sensLuz = Sensor("sensorId1", "Luz")
    val sensHum = Sensor("sensorId2", "Hum")
    val device = Device("deviceId", "house1", pos, List(sensLuz, sensHum))
    device
  }

}
