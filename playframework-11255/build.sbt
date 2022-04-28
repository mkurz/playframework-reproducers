name := "playframework-11255"
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, JavaAgent)
  // Use sbt default layout
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "io.kamon" %% "kamon-bundle" % "2.4.7",
  "io.kamon" %% "kamon-prometheus" % "2.4.7"
)
Compile / doc / sources := Seq.empty
Compile / packageDoc / publishArtifact := false
Test / parallelExecution := true
Test / fork  := false
