package boot

import controllers.DevicesController
import play.api.ApplicationLoader.Context
import play.api.db.slick.{DbName, DefaultSlickApi}
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes
import services.DataSourceJdbc
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

  private[this] val slickApi = new DefaultSlickApi(environment, configuration, applicationLifecycle)
  private[this] val defaultDBName = DbName("devices")
  private[this] val dbConfig = slickApi.dbConfig[JdbcProfile](defaultDBName)

  // TODO put this into config
  private[this] val dbPoolSize = 10
  private[this] lazy val dataSource = new DataSourceJdbc(dbConfig, dbPoolSize)
  private[this] lazy val devicesController = new DevicesController(controllerComponents, dataSource)
  override lazy val router = new Routes(httpErrorHandler, devicesController, assets)
}
