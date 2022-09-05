import Dependencies._

name := "eea"

libraryDependencies ++= scalaTest ++ circe ++ refined
Test / parallelExecution := false