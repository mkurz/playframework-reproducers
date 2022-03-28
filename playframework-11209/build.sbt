name := """playframework-11209"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

Test / javaOptions ++= Seq(
  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
)

Test / fork := true // This is the default anyway
Test / javaOptions ++= Seq(
  "--add-exports=java.base/sun.security.x509=ALL-UNNAMED",
  //"--add-opens=java.base/sun.security.ssl=ALL-UNNAMED"
)
