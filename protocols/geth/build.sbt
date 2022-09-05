import Dependencies._

name := "geth"

libraryDependencies ++= scalaTest ++ circe ++ refined
Test / parallelExecution := false