
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.EthTypes
import org.web3s.abi.codec.TypeEncoder

object EthArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32

abstract class EthArray[T <: EthType[_] : Tag](override val value: Seq[T]) extends EthType[Seq[T]] :

  def this(expectedSize: Int, values: T*) = this(values.toSeq)

  override def bytes32PaddedLength: Int = value.foldLeft(0)(_ + _.bytes32PaddedLength)

  def componentTypeAsString: String = Tag[T].tag.toString.toLowerCase match
    case "ethutf8string" => "string"
    case other => other
 // override def getTypeAsString: String = SolidityTypes.getTypeAString[T] + "[" + values.length + "]"
