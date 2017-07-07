package boot

import controllers.{DevicesController, HomeController}
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.filters.HttpFiltersComponents
import router.Routes

class Boot extends ApplicationLoader {
  def load(context: Context): Application = {
    new BootComponents(context).application
  }
}

class BootComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with controllers.AssetsComponents {

  lazy val homeController = new HomeController(controllerComponents)
  lazy val devicesController = new DevicesController(controllerComponents)
  override lazy val router = new Routes(httpErrorHandler, homeController, devicesController, assets)
}
