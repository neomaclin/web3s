package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*

package primitive:

  import org.web3s.abi.Encodable

  import scala.reflect.Typeable

  sealed trait PrimitiveType[T <: AnyVal] extends SolidityType[T]

  final case class PrimitiveChar(final override val value: Char) extends PrimitiveType[Char] :
    final override val getTypeAsString = "Char"

  final case class PrimitiveShort(final override val value: Short) extends PrimitiveType[Short] :
    final override val getTypeAsString = "Short"

  final case class PrimitiveByte(final override val value: Byte) extends PrimitiveType[Byte] :
    final override val getTypeAsString = "Byte"

  final case class PrimitiveInt(final override val value: Int) extends PrimitiveType[Int] :
    final override val getTypeAsString = "Int"

  final case class PrimitiveLong(final override val value: Long) extends PrimitiveType[Long] :
    final override val getTypeAsString = "Long"

  final case class PrimitiveDouble(final override val value: Double) extends PrimitiveType[Double] :
    final override val getTypeAsString = "Double"

  final case class PrimitiveFloat(final override val value: Float) extends PrimitiveType[Float] :
    final override val getTypeAsString = "Float"


  type AsSolidityType[T <: AnyVal] = T match
    case Byte => Bytes1
    case Short => Int16
    case Int => Int32
    case Long => Int64
    case Char => Utf8String

  extension (x: Short)
    def toSolidityType: AsSolidityType[Short] = new Int16(BigInt(x))

  extension (x: Int)
    def toSolidityType: AsSolidityType[Int] = new Int32(BigInt(x))

  extension (x: Long)
    def toSolidityType: AsSolidityType[Long] = new Int64(BigInt(x))

  extension (x: Char)
    def toSolidityType: AsSolidityType[Char] = new Utf8String(x.toString)

  extension (x: Byte)
    def toSolidityType: AsSolidityType[Byte] = new Bytes1(Array[Byte](x))

  object PrimitiveType:
    def encode[T <:AnyVal: Typeable](v: PrimitiveType[T]): String =
      v.value match
        case x: Byte =>  Bytes.encode(x.toSolidityType)
        case x: Short =>  NumericType.encode(x.toSolidityType)
        case x: Int =>  NumericType.encode(x.toSolidityType)
        case x: Long =>  NumericType.encode(x.toSolidityType)
        case x: Char =>  Utf8String.encode(x.toSolidityType)
        case x: Double =>  throw new UnsupportedOperationException("Type cannot be encoded: Double")
        case x: Float =>  throw new  UnsupportedOperationException("Type cannot be encoded: Float")
    end encode

    def encodePacked[T <:AnyVal: Typeable](v: PrimitiveType[T]): String =
      v.value match
        case x: Byte =>  summon[Encodable[Bytes]].encodePacked(x.toSolidityType)
        case x: Short => summon[Encodable[NumericType]].encodePacked(x.toSolidityType)
        case x: Int =>  summon[Encodable[NumericType]].encodePacked(x.toSolidityType)
        case x: Long =>  summon[Encodable[NumericType]].encodePacked(x.toSolidityType)
        case x: Char =>  summon[Encodable[Utf8String]].encodePacked(x.toSolidityType)
        case x: Double =>  throw new UnsupportedOperationException("Type cannot be encoded: Double")
        case x: Float =>  throw new  UnsupportedOperationException("Type cannot be encoded: Float")
    end encodePacked

//    given Encodable[PrimitiveType[AnyVal]] = new Encodable[PrimitiveType[AnyVal]]:
//      override def encode(value: PrimitiveType[AnyVal]): String = PrimitiveType.encode(value)
//      override def encodePacked(value: PrimitiveType[AnyVal]): String = PrimitiveType.encode(value).substring(62, 64)

  end PrimitiveType

end primitive

