package services

import model.{Device, Position, Sensor}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class DataSource(val dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {

  def devices: List[Device] = {
    List(
      Device(
        "001",
        "CaDani",
        Position(51.461999, 0.125753, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      ),
      Device(
        "002",
        "Adri house",
        Position(51.462485, 0.126115, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      ),
      Device(
        "002",
        "Angelote pelote",
        Position(40.733362, -3.946101, 0),
        List(Sensor("sensorId1", "Luz"), Sensor("sensorId2", "Hum"))
      )
    )
  }
}
