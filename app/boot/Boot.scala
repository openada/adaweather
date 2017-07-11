package boot

import controllers.DevicesController
import play.api.ApplicationLoader.Context
import play.api.db.slick.{DbName, DefaultSlickApi}
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes
import services.DataSource
import slick.jdbc.JdbcProfile

class Boot extends ApplicationLoader {
  def load(context: Context): Application = {
    new BootComponents(context).application
  }
}

class BootComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with controllers.AssetsComponents {

  lazy val dataSource = new DataSource(dbConfig)
  lazy val devicesController = new DevicesController(controllerComponents, dataSource)
  override lazy val router = new Routes(httpErrorHandler, devicesController, assets)
  //TODO DO not use the default execution context for the db
  val slickApi = new DefaultSlickApi(environment, configuration, applicationLifecycle)
  val defaultDBName = DbName("devices")
  val dbConfig = slickApi.dbConfig[JdbcProfile](defaultDBName)
}
