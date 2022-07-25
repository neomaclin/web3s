ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val utils = project

lazy val abi = project.dependsOn(utils)
lazy val rlp = project.dependsOn(utils)
lazy val crypto = project.dependsOn(utils,rlp,abi)
lazy val codegen = project.dependsOn(utils,rlp,abi)

lazy val core =  (project in file("protocols/core")).dependsOn(crypto  % "test->test;compile->compile" ,abi)
lazy val eea = (project in file("protocols/eea")).dependsOn(core).dependsOn(crypto,abi,core)
lazy val besu = (project in file("protocols/besu")).dependsOn(core).dependsOn(crypto,abi,core,eea)

lazy val geth = (project in file("protocols/geth")).dependsOn(core).dependsOn(crypto,abi,core)
lazy val parity = (project in file("protocols/parity")).dependsOn(core).dependsOn(crypto,abi,core)
lazy val providers = (project in file("protocols/providers")).dependsOn(core).dependsOn(crypto,abi,core)

//lazy val contracts = project.dependsOn(core)
lazy val root = (project in file("."))
  .aggregate(besu,eea,geth,parity,providers,core)
  .aggregate(utils,abi,rlp,crypto,codegen)
  .settings(
    name := "web3s",

  )
