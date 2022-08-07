//package org.web3s.abi.codec
//
//import izumi.reflect.Tag
//import org.web3s.abi.datatypes.{EthFunction, EthType}
//import org.web3s.crypto.Hash
//import org.web3s.utils.Numeric
//import Tuple.*
//
//
//object FunctionEncoder:
////  type
////
////  def makeFunction[T <: SolidityType[_] : Tag](fnname: String,
////                                               solidityInputTypes: List[String],
////                                               arguments: List[AnyRef],
////                                               solidityOutputTypes: List[String]): SolidityFunction[T] =
////      val encodedInput =
////      val encodedOutput =
////    SolidityFunction[T](fnname, encodedInput, encodedOutput)
//
//  def buildMethodSignature[H <: EthType[_] : Tag, T <: Tuple](methodName: String, parameters: H *: T) =
//    s"$methodName(${TypeAsStringConverter.convert(parameters)})"
//
//  //def buildMethodSignature
//  def buildMethodId(methodSignature: String): String =
//    val hash = Hash.sha3(methodSignature.getBytes)
//    Numeric.toHexString(hash).substring(0, 10)