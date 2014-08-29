import play.PlayJava

name := """javatt"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "uk.co.panaxiom" %% "play-jongo" % "0.7.1-jongo1.0",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.4.2"
)
