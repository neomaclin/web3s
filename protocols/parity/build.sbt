import Dependencies._

name := "parity"

libraryDependencies ++= scalaTest ++ circe ++ refined
Test / parallelExecution := false