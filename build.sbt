name := """CargoTraffic"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.0.6.Final",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "de.svenkubiak" % "jBCrypt" % "0.4",
  "mysql" % "mysql-connector-java" % "5.1.18"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
PlayKeys.externalizeResources := false
