ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "udemy-scala-begginers",
    idePackagePrefix := Some("com.despegar")
  )
