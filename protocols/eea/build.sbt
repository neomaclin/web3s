import Dependencies._

name := "eea"

libraryDependencies ++= scalaTest ++ circe ++ refined
libraryDependencies ++= Seq(
  "org.scalatestplus" %% "mockito-4-5" % "3.2.12.0" % "test"
)