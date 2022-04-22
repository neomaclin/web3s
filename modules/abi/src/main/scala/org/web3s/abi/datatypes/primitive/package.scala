package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*

package primitive:

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


  //given TypeDecoder[T  <: AnyVal : Ordering] = TypeDecoder.by[T, AsSolidityType](_.toSolidityType)
end primitive

