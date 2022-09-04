
import Dependencies._

name := "core"

libraryDependencies ++= bouncycastle ++ scalaTest ++ circe ++ refined ++ http4s ++ kitten ++ cats ++ fs2
libraryDependencies ++= Seq(
  "org.scalatestplus" %% "mockito-4-5" % "3.2.12.0" % "test"
)

Test / parallelExecution := false
