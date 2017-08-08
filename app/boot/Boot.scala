package boot

import controllers.DevicesController
import play.api.ApplicationLoader.Context
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.evolutions.SlickEvolutionsComponents
import play.api.db.slick.{DbName, DefaultSlickApi}
import play.api.mvc.EssentialFilter
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.headers.SecurityHeadersComponents
import play.filters.hosts.AllowedHostsComponents
import router.Routes
import services.{DataSource, DataSourceJdbc}
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
    with SecurityHeadersComponents
    with AllowedHostsComponents
    with controllers.AssetsComponents
    with EvolutionsComponents
    with SlickEvolutionsComponents {

  def httpFilters: Seq[EssentialFilter] = Seq(securityHeadersFilter, allowedHostsFilter)

  override def api: DefaultSlickApi = new DefaultSlickApi(environment, configuration, applicationLifecycle)
  private[this] val defaultDBName: DbName = DbName("devices")
  private[this] val dbConfig: DatabaseConfig[JdbcProfile] = slickApi.dbConfig[JdbcProfile](defaultDBName)

  // TODO put this into config
  private[this] val dbPoolSize = 10
  private[this] lazy val dataSource: DataSource = new DataSourceJdbc(dbConfig, dbPoolSize)
  private[this] lazy val devicesController: DevicesController = new DevicesController(controllerComponents, dataSource)
  override lazy val router: Routes = new Routes(httpErrorHandler, devicesController, assets)
}
