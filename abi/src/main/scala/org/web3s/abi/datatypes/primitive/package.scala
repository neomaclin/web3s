package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import scala.reflect.Typeable

package primitive:


  sealed trait PrimitiveType[T <: AnyVal : Typeable] extends EthType[T]

  final case class PrimitiveChar(final override val value: Char) extends PrimitiveType[Char]
  // final override val getTypeAsString = "Char"

  final case class PrimitiveShort(final override val value: Short) extends PrimitiveType[Short]
  //final override val getTypeAsString = "Short"

  final case class PrimitiveByte(final override val value: Byte) extends PrimitiveType[Byte]
  //final override val getTypeAsString = "Byte"

  final case class PrimitiveInt(final override val value: Int) extends PrimitiveType[Int]
  //final override val getTypeAsString = "Int"

  final case class PrimitiveLong(final override val value: Long) extends PrimitiveType[Long]
  //final override val getTypeAsString = "Long"

  final case class PrimitiveDouble(final override val value: Double) extends PrimitiveType[Double]
  // final override val getTypeAsString = "Double"

  final case class PrimitiveFloat(final override val value: Float) extends PrimitiveType[Float]
  //  final override val getTypeAsString = "Float"


  type AsEthType[T <: AnyVal] = T match
    case Byte => Bytes1
    case Short => Int16
    case Int => Int32
    case Long => Int64
    case Char => EthUtf8String

  extension (x: Short)
    def asEthType: AsEthType[Short] = Int16(BigInt(x))

  extension (x: Int)
    def asEthType: AsEthType[Int] = Int32(BigInt(x))

  extension (x: Long)
    def asEthType: AsEthType[Long] = Int64(BigInt(x))

  extension (x: Char)
    def asEthType: AsEthType[Char] = EthUtf8String(x.toString)

  extension (x: Byte)
    def asEthType: AsEthType[Byte] = Bytes1(Array[Byte](x))
