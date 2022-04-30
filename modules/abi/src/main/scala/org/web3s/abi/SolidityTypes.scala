package org.web3s.abi

import izumi.reflect.Tag
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.datatypes.primitive.*

object SolidityTypes:

  def getType(`type`: String, primitives: Boolean): Tag[_ <: SolidityType[_]] =
    `type`.toLowerCase match
      case "address" =>
        Tag[Address]
      case "bool" | "boolean" =>
        Tag[Bool]
      case "string" =>
        Tag[Utf8String]
      case "bytes" =>
        Tag[DynamicBytes]
      case "byte" =>
        Tag[PrimitiveByte]
      case "char" =>
        Tag[PrimitiveChar]
      case "double" =>
        Tag[PrimitiveDouble]
      case "float" =>
        Tag[PrimitiveFloat]
      case "uint" =>
        Tag[SolidityUInt]
      case "short" =>
        Tag[PrimitiveShort]
      case "int" =>
        Tag[PrimitiveInt]
      case "long" =>
        Tag[PrimitiveLong]
      case "uint8" =>
         Tag[UInt8]
      case "int8" =>
        if primitives then Tag[PrimitiveShort] else Tag[Int8]
      case "uint16" =>
        if primitives then Tag[PrimitiveInt] else Tag[UInt16]
      case "int16" =>
        if primitives then Tag[PrimitiveInt] else Tag[Int16]
      case "uint24" =>
        if primitives then Tag[PrimitiveInt] else Tag[UInt24]
      case "int24" =>
        if primitives then Tag[PrimitiveInt] else Tag[Int24]
      case "uint32" =>
        if primitives then Tag[PrimitiveLong] else Tag[UInt32]
      case "int32" =>
        if primitives then Tag[PrimitiveInt] else Tag[Int32]
      case "uint40" =>
        if primitives then Tag[PrimitiveLong] else Tag[UInt40]
      case "int40" =>
        if primitives then Tag[PrimitiveLong] else Tag[Int40]
      case "uint48" =>
        if primitives then Tag[PrimitiveLong] else Tag[UInt48]
      case "int48" =>
        if primitives then Tag[PrimitiveLong] else Tag[Int48]
      case "uint56" =>
        if primitives then Tag[PrimitiveLong] else Tag[UInt56]
      case "int56" =>
        if primitives then Tag[PrimitiveLong] else Tag[Int56]
      case "uint64" =>
        Tag[UInt64]
      case "int64" =>
        if primitives then Tag[PrimitiveLong] else Tag[Int64]
      case "uint72" =>
        Tag[UInt72]
      case "int72" =>
        Tag[Int72]
      case "uint80" =>
        Tag[UInt80]
      case "int80" =>
        Tag[Int80]
      case "uint88" =>
        Tag[UInt88]
      case "int88" =>
        Tag[Int88]
      case "uint96" =>
        Tag[UInt96]
      case "int96" =>
        Tag[Int96]
      case "uint104" =>
        Tag[UInt104]
      case "int104" =>
        Tag[Int104]
      case "uint112" =>
        Tag[UInt112]
      case "int112" =>
        Tag[Int112]
      case "uint120" =>
        Tag[UInt120]
      case "int120" =>
        Tag[Int120]
      case "uint128" =>
        Tag[UInt128]
      case "int128" =>
        Tag[Int128]
      case "uint136" =>
        Tag[UInt136]
      case "int136" =>
        Tag[Int136]
      case "uint144" =>
        Tag[UInt144]
      case "int144" =>
        Tag[Int144]
      case "uint152" =>
        Tag[UInt152]
      case "int152" =>
        Tag[Int152]
      case "uint160" =>
        Tag[UInt160]
      case "int160" =>
        Tag[Int160]
      case "uint168" =>
        Tag[UInt168]
      case "int168" =>
        Tag[Int168]
      case "uint176" =>
        Tag[UInt176]
      case "int176" =>
        Tag[Int176]
      case "uint184" =>
        Tag[UInt184]
      case "int184" =>
        Tag[Int184]
      case "uint192" =>
        Tag[UInt192]
      case "int192" =>
        Tag[Int192]
      case "uint200" =>
        Tag[UInt200]
      case "int200" =>
        Tag[Int200]
      case "uint208" =>
        Tag[UInt208]
      case "int208" =>
        Tag[Int208]
      case "uint216" =>
        Tag[UInt216]
      case "int216" =>
        Tag[Int216]
      case "uint224" =>
        Tag[UInt224]
      case "int224" =>
        Tag[Int224]
      case "uint232" =>
        Tag[UInt232]
      case "int232" =>
        Tag[Int232]
      case "uint240" =>
        Tag[UInt240]
      case "int240" =>
        Tag[Int240]
      case "uint248" =>
        Tag[UInt248]
      case "int248" =>
        Tag[Int248]
      case "uint256" =>
        Tag[UInt256]
      case "int256" =>
        Tag[Int256]
      case "bytes1" =>
        Tag[Bytes1]
      case "bytes2" =>
        Tag[Bytes2]
      case "bytes3" =>
        Tag[Bytes3]
      case "bytes4" =>
        Tag[Bytes4]
      case "bytes5" =>
        Tag[Bytes5]
      case "bytes6" =>
        Tag[Bytes6]
      case "bytes7" =>
        Tag[Bytes7]
      case "bytes8" =>
        Tag[Bytes8]
      case "bytes9" =>
        Tag[Bytes9]
      case "bytes10" =>
        Tag[Bytes10]
      case "bytes11" =>
        Tag[Bytes11]
      case "bytes12" =>
        Tag[Bytes12]
      case "bytes13" =>
        Tag[Bytes13]
      case "bytes14" =>
        Tag[Bytes14]
      case "bytes15" =>
        Tag[Bytes15]
      case "bytes16" =>
        Tag[Bytes16]
      case "bytes17" =>
        Tag[Bytes17]
      case "bytes18" =>
        Tag[Bytes18]
      case "bytes19" =>
        Tag[Bytes19]
      case "bytes20" =>
        Tag[Bytes20]
      case "bytes21" =>
        Tag[Bytes21]
      case "bytes22" =>
        Tag[Bytes22]
      case "bytes23" =>
        Tag[Bytes23]
      case "bytes24" =>
        Tag[Bytes24]
      case "bytes25" =>
        Tag[Bytes25]
      case "bytes26" =>
        Tag[Bytes26]
      case "bytes27" =>
        Tag[Bytes27]
      case "bytes28" =>
        Tag[Bytes28]
      case "bytes29" =>
        Tag[Bytes29]
      case "bytes30" =>
        Tag[Bytes30]
      case "bytes31" =>
        Tag[Bytes31]
      case "bytes32" =>
        Tag[Bytes32]
      case _ => throw new UnsupportedOperationException("Unsupported type encountered: " + `type`)
    end match
  end getType

  private val uintR = """UInt(\d*)""".r
  private val intR = """Int(\d*)""".r

  def typeLengthOf[T <: NumericType : Tag]:Int =
    Tag[T].tag.toString match
      case uintR(size) => size.toInt
      case intR(size) => size.toInt
      case _ => SolidityType.MAX_BIT_LENGTH
  end typeLengthOf

  def typeLengthInBytes[T <: NumericType: Tag]:Int = typeLengthOf[T] >> 3
    

  def getTypeAString[T:Tag]: String = Tag[T].tag.toString.toLowerCase match
    case "utf8string" => "string"
    case "solidityuint" => "uint"
    case "solidityint" => "int"
    case other => other
//
//    case "string" =>
//      Tag[Utf8String]
//    case "bytes" =>
//      Tag[DynamicBytes]
//    case "byte" =>
//      Tag[PrimitiveByte]
//    case "char" =>
//      Tag[PrimitiveChar]
//    case "double" =>
//      Tag[PrimitiveDouble]
//    case "float" =>
//      Tag[PrimitiveFloat]
//    case "uint" =>
//      Tag[SolidityUInt]
//    case "short" =>
//      Tag[PrimitiveShort]
//    case "int" =>
//      Tag[PrimitiveInt]
//    case "long" =>
//      Tag[PrimitiveLong]
//    case "uint8" =>
//      Tag[UInt8]
//    case "int8" =>
//      if primitives then Tag[PrimitiveShort] else Tag[Int8]
//    case "uint16" =>
//      if primitives then Tag[PrimitiveInt] else Tag[UInt16]
//    case "int16" =>
//      if primitives then Tag[PrimitiveInt] else Tag[Int16]
//    case "uint24" =>
//      if primitives then Tag[PrimitiveInt] else Tag[UInt24]
//    case "int24" =>
//      if primitives then Tag[PrimitiveInt] else Tag[Int24]
//    case "uint32" =>
//      if primitives then Tag[PrimitiveLong] else Tag[UInt32]
//    case "int32" =>
//      if primitives then Tag[PrimitiveInt] else Tag[Int32]
//    case "uint40" =>
//      if primitives then Tag[PrimitiveLong] else Tag[UInt40]
//    case "int40" =>
//      if primitives then Tag[PrimitiveLong] else Tag[Int40]
//    case "uint48" =>
//      if primitives then Tag[PrimitiveLong] else Tag[UInt48]
//    case "int48" =>
//      if primitives then Tag[PrimitiveLong] else Tag[Int48]
//    case "uint56" =>
//      if primitives then Tag[PrimitiveLong] else Tag[UInt56]
//    case "int56" =>
//      if primitives then Tag[PrimitiveLong] else Tag[Int56]
//    case "uint64" =>
//      Tag[UInt64]
//    case "int64" =>
//      if primitives then Tag[PrimitiveLong] else Tag[Int64]



end SolidityTypes

