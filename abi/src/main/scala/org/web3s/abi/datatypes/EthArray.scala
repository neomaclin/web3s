
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.EthTypes
import org.web3s.abi.codec.TypeEncoder

object EthArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32

abstract class EthArray[T <: EthType[_] : Tag] extends EthType[Array[T]] :
  def value: Array[T]
  override def bytes32PaddedLength: Int = value.foldLeft(0)(_ + _.bytes32PaddedLength)
  def componentTypeAsString: String = Tag[T].tag.toString.toLowerCase match
    case "ethutf8string" => "string"
    case "ethuint" => "uint"
    case "ethint" => "int"
    case other => other
