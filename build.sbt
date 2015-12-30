name := """CargoTraffic"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.0.6.Final",
  "de.svenkubiak" % "jBCrypt" % "0.4",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.apache.commons" % "commons-collections4" % "4.1"
)

routesGenerator := InjectedRoutesGenerator
PlayKeys.externalizeResources := false
