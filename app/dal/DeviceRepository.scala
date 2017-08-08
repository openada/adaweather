package dal

import model.db.DeviceDb
import model.request.NewDevice
import model.{Device, Position, Sensor}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.lifted.{ForeignKeyQuery, MappedProjection, ProvenShape}

import scala.concurrent.{ExecutionContext, Future}
import scala.collection.immutable.Iterable

class DeviceRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  /**
    * Here we define the table. It will have a name of people
    */
  private class DeviceTable(tag: Tag) extends Table[DeviceDb](tag, "DEVICES") {

    def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("NAME")

    def latitude: Rep[Double]   = column[Double]("LATITUDE")
    def longitude: Rep[Double]  = column[Double]("LONGITUDE")
    def altitude: Rep[Double]   = column[Double]("ALTITUDE")

    def position: MappedProjection[Position, (Double, Double, Double)] = (latitude, longitude, altitude) <> ((Position.apply _).tupled, Position.unapply)

    def * : ProvenShape[DeviceDb] = (id, name, position) <> ((DeviceDb.apply _).tupled, DeviceDb.unapply)
  }

  private val device = TableQuery[DeviceTable]

  private class SensorTable(tag: Tag) extends Table[Sensor](tag, "SENSORS") {

    def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("NAME")

    def * : ProvenShape[Sensor] = (id, name) <> ((Sensor.apply _).tupled, Sensor.unapply)
  }

  private val sensor = TableQuery[SensorTable]

  private class DeviceSensorTable(tag: Tag) extends Table[(Long, Long)](tag, "DEVICES_SENSORS") {

    def deviceId: Rep[Long] = column[Long]("DEVICE_ID", O.PrimaryKey)

    def sensorId: Rep[Long] = column[Long]("SENSOR_ID", O.PrimaryKey)

    def * : ProvenShape[(Long, Long)] = (deviceId, sensorId)

    def deviceFk: ForeignKeyQuery[DeviceTable, DeviceDb] = foreignKey("device_fk", deviceId, device)(_.id)
    def sensonFk: ForeignKeyQuery[SensorTable, Sensor] = foreignKey("senson_fk", deviceId, sensor)(_.id)
  }

  private val deviceSensor = TableQuery[DeviceSensorTable]

  // TODO I believe this can be simplified a lot and way more readable
  def findAll: Future[Iterable[Device]] = {
    db.run {
      val query = for {
        ((devices, _), sensors) <- device joinLeft deviceSensor on (_.id === _.deviceId) joinLeft sensor on (_._2.map(_.sensorId) === _.id)
      } yield (devices, sensors)

      query.result
    }.map(
      _.groupBy { case (dev, _) => dev} // Group by the device
        .map { case (d, s) =>
          Device(d, s.flatMap { case (_, sens) => sens}.toList)
      } // Get the right device based on the grouped sensors
    )
  }

  def findAllSensors: Future[Seq[Sensor]] = {
    db.run(sensor.result)
  }

  def addDevice(newDevice: NewDevice): Future[DeviceDb] = {
    val query =for {
      deviceId <- (device returning device.map(_.id)) += DeviceDb(0, newDevice.name, Position(0,0,0))
      _ <- deviceSensor ++= newDevice.sensorIds.map(sensorId => deviceId -> sensorId)
    } yield deviceId

    db.run(query.transactionally) map { deviceId =>
      DeviceDb(deviceId, newDevice.name, Position(0,0,0))
    }

  }

}