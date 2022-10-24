name := "local-website"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice

PlayKeys.devSettings += "play.server.http.port" -> "9001"
