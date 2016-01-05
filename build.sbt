name := "scala-sql-parser"

organization := "com.stephentu"

scalaVersion in ThisBuild := "2.11.7"

version := "0.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
      "org.specs2" %% "specs2" % "2.3.12" % "test"
    )
  )

publishTo := {
  val host = "https://flow.artifactoryonline.com/flow"
  if (isSnapshot.value) {
    Some("Artifactory Realm" at s"$host/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime)
  } else {
    Some("Artifactory Realm" at s"$host/libs-release-local")
  }
}
