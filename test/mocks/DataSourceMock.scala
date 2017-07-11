package mocks

import model.{Device, Position, Sensor}
import org.mockito.Mockito.{mock, when}
import services.DataSource

/**
  * Created by einevea on 10/07/2017.
  */
trait DataSourceMock {
  val dataSourceMock = mock[DataSource](classOf[DataSource])
  when(dataSourceMock.devices).thenReturn(
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
  )
}
