package services

import java.util.concurrent.ForkJoinPool

import model.{Device, Position, Sensor}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

trait DataSource {
  def devices: List[Device]
  def executionContext: ExecutionContext
}

class DataSourceJdbc(val dbConfig: DatabaseConfig[JdbcProfile], paralelism: Int) extends DataSource {

  override val executionContext: ExecutionContext = ExecutionContext.fromExecutorService(new ForkJoinPool(paralelism))

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
