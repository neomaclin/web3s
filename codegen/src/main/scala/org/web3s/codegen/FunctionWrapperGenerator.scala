package org.web3s.codegen

import java.io.File

object FunctionWrapperGenerator:
  val SCALA_TYPES_ARG = "--scalaTypes"
  val SOLIDITY_TYPES_ARG = "--solidityTypes"
  val PRIMITIVE_TYPES_ARG = "--primitiveTypes"
  val ABI_FUNCS = "--abiFuncs"
//
//  // We'll start by defining our individual options...
//  val uriOpt = Opts.option[String]("scalaTypes", "Location of the remote file.")
//  // uriOpt: Opts[URI] = Opts(--scalaTypes <uri>)
//  val timeoutOpt =
//    Opts.option[Duration]("solidityTypes", "Timeout for fetching the remote file.")
//      .withDefault(Duration.Inf)
//  // timeoutOpt: Opts[Duration] = Opts([--solidityTypes <duration>])
//  val fileOpt = Opts.option[Path]("input-file", "Local path to input file.")
//  // fileOpt: Opts[Path] = Opts(--input-file <path>)
//

  def getFileNameNoExtension(fileName: String) = fileName.split("\\.(?=[^.]*$)").head

  def useScalaNativeTypes(argVal: String, usageString: String): Boolean =
    argVal match
      case SOLIDITY_TYPES_ARG => false
      case SCALA_TYPES_ARG => true
      case _ => throw new IllegalArgumentException(usageString)
