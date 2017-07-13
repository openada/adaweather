name := """adaweather"""
organization := "com.ada"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies += jdbc % Test

//Database dependencies:
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
  "com.h2database" % "h2" % "1.4.195"
)
libraryDependencies += evolutions

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ada.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ada.binders._"

coverageExcludedPackages := "<empty>;Reverse.*;router.Routes;router.RoutesPrefix"
