package services

import java.util.concurrent.ForkJoinPool

import dal.DeviceRepository
import model.Device
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.collection.immutable.Iterable
import scala.concurrent.{ExecutionContext, Future}

trait DataSource {
  def deviceRepository: DeviceRepository
  def executionContext: ExecutionContext
}

class DataSourceJdbc(val dbConfig: DatabaseConfig[JdbcProfile], paralelism: Int) extends DataSource {

  override implicit val executionContext: ExecutionContext = ExecutionContext.fromExecutorService(new ForkJoinPool(paralelism))

  val deviceRepository = new DeviceRepository(dbConfig)
}

