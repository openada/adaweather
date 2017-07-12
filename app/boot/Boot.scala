package boot

import controllers.DevicesController
import play.api.ApplicationLoader.Context
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.evolutions.SlickEvolutionsComponents
import play.api.db.slick.{DbName, DefaultSlickApi}
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes
import services.DataSource
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class Boot extends ApplicationLoader {
  def load(context: Context): Application = {
    val components = new BootComponents(context)
    components.applicationEvolutions // Run the evolutions
    components.application
  }
}

class BootComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with controllers.AssetsComponents
    with EvolutionsComponents
    with SlickEvolutionsComponents {

  lazy val dataSource: DataSource = new DataSource(dbConfig)
  lazy val devicesController: DevicesController = new DevicesController(controllerComponents, dataSource)
  override lazy val router: Routes = new Routes(httpErrorHandler, devicesController, assets)
  val defaultDBName: DbName = DbName("devices")
  val dbConfig: DatabaseConfig[JdbcProfile] = slickApi.dbConfig[JdbcProfile](defaultDBName)

  //TODO DO not use the default execution context for the db
  override def api: DefaultSlickApi = new DefaultSlickApi(environment, configuration, applicationLifecycle)
}
