package mocks

import model.{Device, Position, Sensor}
import services.DataSource

import scala.collection.immutable.Iterable
import scala.concurrent.{ExecutionContext, Future}

/**
  * Provides different common mocks to inject on tests
  */
object DataSourceMock {

  val mockDevices = new DataSource {

    override val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global

    override def devices: Future[Iterable[Device]] = Future.successful(List(
      Device(
        1,
        "CaDani",
        Position(51.461999, 0.125753, 0),
        List(Sensor(1, "Luz"), Sensor(2, "Hum"))
      ),
      Device(
        2,
        "Adri house",
        Position(51.462485, 0.126115, 0),
        List(Sensor(1, "Luz"), Sensor(2, "Hum"))
      ),
      Device(
        3,
        "Angelote pelote",
        Position(40.733362, -3.946101, 0),
        List(Sensor(1, "Luz"), Sensor(2, "Hum"))
      )
    ))
  }

}
