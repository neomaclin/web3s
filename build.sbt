ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val utils = project in file("modules/utils")
lazy val abi = (project in file("modules/abi")).dependsOn(utils)
lazy val rlp = (project in file("modules/rlp")).dependsOn(utils)
lazy val crypto = (project in file("modules/crypto")).dependsOn(utils,rlp,abi)

lazy val root = (project in file("."))
  .dependsOn(crypto)
  .aggregate(utils,abi,rlp,crypto)
  .settings(
    name := "web3s"
  )
