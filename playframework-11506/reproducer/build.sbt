name := "reproducer-11506"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
//  .enablePlugins(PlayNettyServer)
//  .disablePlugins(PlayAkkaHttpServer)

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
  , "com.googlecode.owasp-java-html-sanitizer" % "owasp-java-html-sanitizer" % "20220608.1"
  , caffeine
  , ws
)
