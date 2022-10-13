addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.17")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.5")

ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
