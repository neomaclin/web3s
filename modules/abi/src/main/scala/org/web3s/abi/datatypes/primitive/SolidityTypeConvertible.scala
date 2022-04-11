package org.web3s.abi.datatypes.primitive

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*

trait SolidityTypeConvertible[T : Ordering]:
  extension(x: T) def toSolidityType: SolidityType[_]

given SolidityTypeConvertible[Byte] with
  extension(x: Byte) def toSolidityType: SolidityType[Array[Byte]] = new Bytes1(Array[Byte](x))

given SolidityTypeConvertible[Char] with
  extension(x: Char) def toSolidityType: SolidityType[String] = new Utf8String(String.valueOf(x))

given SolidityTypeConvertible[Int] with
  extension(x: Int) def toSolidityType: SolidityType[BigInt] = new Int32(x)

given SolidityTypeConvertible[Long] with
  extension(x: Long) def toSolidityType: SolidityType[BigInt] = new Int64(x)

given SolidityTypeConvertible[Short] with
  extension(x: Short) def toSolidityType: SolidityType[BigInt] = new Int16(x)