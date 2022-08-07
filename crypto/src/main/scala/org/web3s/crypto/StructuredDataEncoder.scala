package org.web3s.crypto

import io.circe.{Json, JsonObject}
import io.circe.Json.{JArray, JBoolean, JNull, JNumber, JObject, JString}
import izumi.reflect.macrortti.LightTypeTagRef.SymName.SymTypeName
import org.web3s.abi.EthTypes
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.crypto.Hash.{sha3, sha3String}
import org.web3s.crypto.StructuredDataEncoder.convertArgToBytes
import org.web3s.utils.Numeric

import java.io.IOException
import scala.util.Try


object StructuredDataEncoder:

  private val arrayTypeRegex = """^([a-zA-Z_$][a-zA-Z_$0-9]*)((\\[([1-9]\\d*)?\\])+)$""".r
  private val bytesTypeRegex = """^bytes[0-9][0-9]?$""".r
  private val arrayDimensionRegex = """\\[([1-9]\\d*)?\\]""".r

  @throws[Exception]
  private def convertArgToBytes(inputValue: String): Array[Byte] =
    var hexValue = inputValue
    if (!Numeric.containsHexPrefix(inputValue))
      val value = Try(BigInt(inputValue)).getOrElse(BigInt(inputValue, 16))

      hexValue = Numeric.toHexStringNoPrefix(value.toByteArray)
      // fix sign condition
      if (hexValue.length > 64 && hexValue.startsWith("00")) hexValue = hexValue.substring(2)
    end if

    Numeric.hexStringToByteArray(hexValue)
  end convertArgToBytes

end StructuredDataEncoder


class StructuredDataEncoder(jsonMessageInString: String): 

  import io.circe.parser.decode

  val messageObject: StructuredData.EIP712Message = decode[StructuredData.EIP712Message](jsonMessageInString).toTry.get

  // Find all the dependencies of a type
  def getDependencies(primaryType: String): Set[String] =
    val types = messageObject.types
    types.get(primaryType)
      .map(primaryType :: _.view.filter(entry => types.contains(entry.`type`.value)).map(_.`type`.value).toList)
      .getOrElse(Nil).toSet
  end getDependencies

  def encodeStruct(structName: String): String =
    messageObject.types.get(structName)
      .map(_.map(entry => s"${entry.`type`.value} ${entry.name.value}").mkString(s"$structName(", ",", ")"))
      .getOrElse(s"$structName()")
  end encodeStruct

  def encodeType(primaryType: String): String =
    (primaryType :: getDependencies(primaryType).filterNot(_ == primaryType).toList.sorted).map(encodeStruct).mkString
  end encodeType

  def typeHash(primaryType: String): Array[Byte] = Numeric.hexStringToByteArray(sha3String(encodeType(primaryType)))

  private def convertToBigInt(value: Json): BigInt =
    value.asString match
      case Some(v: String) => if v.startsWith("0x") then Numeric.toBigInt(v) else BigInt(v)
      case None => BigInt(0)
  end convertToBigInt


  def getArrayDimensionsFromDeclaration(declaration: String): List[Int] = // Get the dimensions which were declared in Schema.
    declaration match
      case StructuredDataEncoder.arrayTypeRegex(_, StructuredDataEncoder.arrayDimensionRegex(entries*)) =>
        entries.map(entry => Try(entry.toInt).getOrElse(-1)).toList
      case _ => Nil
    end match
  end getArrayDimensionsFromDeclaration

  def getDepthsAndDimensions(data: Json, depth: Int): List[(Int, Int)] =
    data.asArray match
      case Some(data) => depth -> data.size :: data.map(getDepthsAndDimensions(_, depth + 1)).reduce(_ ++ _)
      case None => Nil
  end getDepthsAndDimensions

  def getArrayDimensionsFromData(data: Json): List[Int] =
    getDepthsAndDimensions(data, 0)
      .groupMap(_._1)(_._2).view
      .values
      .flatMap(_.distinct)
      .toList


  def flattenMultidimensionalArray(data: Json): List[Json] =
    if data.isArray then
      data.asArray.map(_.flatMap(flattenMultidimensionalArray)).toList.flatten
    else
      data :: Nil
  end flattenMultidimensionalArray

  private def convertToEncodeItem(baseType: String, data: Json): Array[Byte] =
    Try {
      baseType.toLowerCase match
        case x if x.startsWith("uint") || x.startsWith("int") =>
          val value = convertToBigInt(data)
          if (value.signum >= 0)
            Numeric.toBytesPadded(convertToBigInt(data), MAX_BYTE_LENGTH)
          else
            val rawValue: Array[Byte] = value.toByteArray
            val result: Array[Byte] = Array.fill[Byte](MAX_BYTE_LENGTH)(0xff.toByte)
            Array.copy(rawValue,0,result,MAX_BYTE_LENGTH - rawValue.length, rawValue.length)
            result
        case "string" => data.asString.getOrElse("").getBytes
        case "bytes" => Numeric.hexStringToByteArray(data.asString.getOrElse(""))
        case _ => convertArgToBytes(data.asString.getOrElse(""))
      end match
    }.getOrElse(Array.empty[Byte])
  end convertToEncodeItem

  private def getArrayItems(field: StructuredData.Entry, value: Json): List[Json] =
    val expectedDimensions = getArrayDimensionsFromDeclaration(field.`type`.value)
    val dataDimensions = getArrayDimensionsFromData(value)
    val format = s"Array Data $value has dimensions ${dataDimensions.mkString(",")}, but expected dimensions are ${expectedDimensions.mkString(",")}"

    if (expectedDimensions.size != dataDimensions.size) || (expectedDimensions.zip(dataDimensions).filterNot(_._1 == -1).exists(_!=_)) then
      throw new RuntimeException(format)
    else
      flattenMultidimensionalArray(value)

  end getArrayItems

  def encodeData(primaryType: String, data: Map[String, Json]):Array[Byte] = ???
  //   val types = messageObject.types
  //   var encTypes = "bytes32" :: Nil
  //   var encValues = typeHash(primaryType) :: Nil

  //   for
  //     field <- types(primaryType)
  //   do
  //     for
  //       value <- data.get(field.name.value)
  //     do
  //       field.`type`.value match
  //         case "string" =>
  //           encTypes = "bytes32" :: encTypes
  //           encValues =  Numeric.hexStringToByteArray(sha3String(value.asString.get)) :: encValues
  //         case "bytes" =>
  //           encTypes = "bytes32" :: encTypes
  //           encValues = sha3(Numeric.hexStringToByteArray(value.asString.get)) :: encValues
  //         case x if types.contains(x) =>
  //           encTypes = "bytes32" :: encTypes
  //           encValues = sha3(encodeData(x, value.as[Map[String,Json]].getOrElse(Map.empty))) :: encValues
  //         case x if StructuredDataEncoder.bytesTypeRegex.matches(x) =>
  //           encTypes = x :: encTypes
  //           encValues = Numeric.hexStringToByteArray(value.asString.get) :: encValues
  //         case x if StructuredDataEncoder.arrayTypeRegex.matches(x) =>
  //           val baseTypeName = x.takeWhile(_ != '[')
  //           val arrayItems = getArrayItems(field, value)
  //           val arrayEncoding =
  //             if types.contains(baseTypeName) then
  //               arrayItems.map(item=>sha3(encodeData(baseTypeName, item.as[Map[String,Json]].getOrElse(Map.empty))))
  //             else
  //               arrayItems.map(convertToEncodeItem(baseTypeName, _))
  //           encTypes = "bytes32" :: encTypes
  //           encValues = sha3(arrayEncoding.reduce(_++_)) :: encValues
  //         case x if x.startsWith("uint") || x.startsWith("int") =>
  //           encTypes = x :: encTypes
  //           encValues = convertToBigInt(value).toByteArray :: encValues
  //         case x =>
  //           encTypes = x :: encTypes

  //       end match

  //     end for
  //   end for
  // end encodeData
  // private def encodeType(typeName: String, data: Json): Array[Byte] =
  //   val tag = SolidityTypes.getTypeTag(typeName)
  //   data  match
  //     case JNull       => Array.empty[Byte]
  //     case JBoolean(b) =>
  //     case JNumber(n)  =>
  //     case JString(s)  =>
  //     case JArray(a)   =>
  //     case JObject(o)  => encodeType(typeName, o.toMap)
  //   end match

  // end encodeType

  def hashMessage(primaryType: String, data: Map[String, Json]):Array[Byte]  = sha3(encodeData(primaryType, data))

  import io.circe.syntax._
  def hashDomain: Array[Byte] =
    val domain = messageObject.domain
    val domainData:Map[String,Json] = Map(
      "name" -> domain.name.asJson,
      "version" -> domain.version.asJson,
      "verifyingContract" -> domain.verifyingContract.value.asJson )
      ++ domain.chainId.map("chainId" -> _.value.asJson)
      ++ domain.salt.map("salt" -> _.asJson)

    sha3(encodeData("EIP712Domain", domainData))
  end hashDomain

  def structuredData: Array[Byte] = "\u0019\u0001".getBytes ++ hashDomain ++ hashMessage(messageObject.primaryType, messageObject.message)

  def hashStructuredData: Array[Byte] = sha3(structuredData)

end StructuredDataEncoder
