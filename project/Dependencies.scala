import sbt.{ExclusionRule, _}

object Dependencies {
  object versions {
    val scala2 = "2.13.8"
    val scala3 = "3.1.2"
    val cats = "2.7.0"
    val kitten = "3.0.0-M3"

    val tapir = "0.19.3"
    val circe = "0.14.1"
    val doobie = "1.0.0-RC1"
    val configSupport = "0.17.1"
    val bytebuddy = "1.12.9"
    val scalaTest = "3.2.12"
    val scalaMock = "5.2.0"
    val mockito = "1.17.0"
    val scalaLogging = "3.9.4"
    val logback = "1.2.10"
    val refined = "0.9.28"
    val http4s = "0.23.12"
    val jwt = "9.0.4"
    val log4j = "2.17.2"
    val fs2 = "3.2.8"
    val munit = "1.0.0-M1"
    val munitce3 = "1.0.7"
  }

  val fs2 = Seq(
    "co.fs2" %% "fs2-core" % versions.fs2,
    "co.fs2" %% "fs2-io" % versions.fs2,

  )

  val http4s =  Seq(
    "org.http4s"      %% "http4s-ember-server" % versions.http4s,
    "org.http4s"      %% "http4s-ember-client" % versions.http4s,
    "org.http4s"      %% "http4s-circe"        % versions.http4s,
    "org.http4s"      %% "http4s-dsl"          % versions.http4s,
  )


  val kitten = Seq("org.typelevel" %% "kittens").map(_ % versions.kitten)

  val cats = Seq(
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-kernel",
  ).map(_ % versions.cats)

  val reflection = Seq(
    "dev.zio" %% "izumi-reflect" % "2.1.0",
  )


  val configSupport = Seq(
    "com.github.pureconfig" %% "pureconfig" % versions.configSupport,
  )

  val circe = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-refined",
  ).map(_ % versions.circe)

  val doobie = Seq(
    "org.tpolecat" %% "doobie-core" % versions.doobie,
    "org.tpolecat" %% "doobie-postgres" % versions.doobie,
    // "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC1" % Test,
  )


  val scalaTest = Seq(
    "org.scalatest"   %% "scalatest"           % versions.scalaTest % Test,
    "org.typelevel"   %% "cats-effect-testing-scalatest" % "1.4.0"  % Test,
    "org.scalameta"   %% "munit"               % versions.munit           % Test,
    "org.typelevel"   %% "munit-cats-effect-3" % versions.munitce3        % Test,

  )

  val scalaMock = Seq(
    "org.scalamock" %% "scalamock" % versions.scalaMock,
    "org.mockito" %% "mockito-scala" % versions.mockito excludeAll ExclusionRule(
      organization = "net.bytebuddy",
    ),
    "net.bytebuddy" % "byte-buddy" % versions.bytebuddy,
    "net.bytebuddy" % "byte-buddy-agent" % versions.bytebuddy,
  )


  val loggingDependencies = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % versions.scalaLogging,
  )

  val refined = Seq(
    "eu.timepit" %% "refined" % versions.refined,
    "eu.timepit" %% "refined-cats" % versions.refined, // optional
  )
  

  val bouncycastle = Seq(
    "org.bouncycastle" % "bcprov-jdk15on" % "1.70",
    "org.bouncycastle" % "bcpkix-jdk15on" % "1.70"

  )


  val logger = Seq(
    "org.apache.logging.log4j" % "log4j-api" % versions.log4j,
    "org.apache.logging.log4j" % "log4j-core" % versions.log4j,
    "org.apache.logging.log4j" % "log4j-slf4j-impl" % versions.log4j,
  )


}
