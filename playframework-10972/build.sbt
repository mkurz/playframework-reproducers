name := """playframework-10972"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

envVars += ("mytestvar" -> "10")

Test / fork := true

libraryDependencies += guice
