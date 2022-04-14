package org.web3s.crypto

import io.circe.Json
import io.circe.Json.JString
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.crypto.Hash.{sha3, sha3String}
import org.web3s.crypto.StructuredDataEncoder.convertArgToBytes
import org.web3s.utils.Numeric

import java.io.IOException
import scala.quoted.*
import scala.reflect.Typeable
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

  private def convertToBigInt[T:Typeable](value: T): BigInt =
    value match
      case v: String => if v.startsWith("0x") then Numeric.toBigInt(v) else BigInt(v)
      case _ => BigInt(0)
  end convertToBigInt


  def getArrayDimensionsFromDeclaration(declaration: String): List[Int] = // Get the dimensions which were declared in Schema.
    declaration match
      case StructuredDataEncoder.arrayTypeRegex(_, StructuredDataEncoder.arrayDimensionRegex(entries*)) =>
        entries.map(entry => Try(entry.toInt).getOrElse(-1)).toList
      case _ => Nil
    end match
  end getArrayDimensionsFromDeclaration

  def getDepthsAndDimensions[T: Typeable](data: T, depth: Int): List[(Int, Int)] =
    data match
      case data: List[_] => depth -> data.size :: data.map(getDepthsAndDimensions(_, depth + 1)).reduce(_ ++ _)
      case _ => Nil
    end match
  end getDepthsAndDimensions

  def getArrayDimensionsFromData[T: Typeable](data: T): List[Int] =
    getDepthsAndDimensions(data, 0)
      .groupMap(_._1)(_._2).view
      .values
      .flatMap(_.distinct)
      .toList


  def flattenMultidimensionalArray[T: Typeable](data: T): List[Any] =
    data match
      case data: List[_] => data.map(flattenMultidimensionalArray).reduce(_ ++ _)
      case a  => List(a)
    end match
  end flattenMultidimensionalArray

  private def convertToEncodeItem[T:Typeable](baseType: String, data: T): Array[Byte] =
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
        case "string" => data.asInstanceOf[String].getBytes("UTF-8")
        case "bytes" => Numeric.hexStringToByteArray(data.asInstanceOf[String])
        case _ => convertArgToBytes(data.asInstanceOf[String])
      end match
    }.getOrElse(Array.empty[Byte])
  end convertToEncodeItem

  private def getArrayItems[T:Typeable](field: StructuredData.Entry, value: T): List[Any] =
    val expectedDimensions = getArrayDimensionsFromDeclaration(field.`type`.value)
    val dataDimensions = getArrayDimensionsFromData(value)
    val format = s"Array Data $value has dimensions ${dataDimensions.mkString(",")}, but expected dimensions are ${expectedDimensions.mkString(",")}"

    if (expectedDimensions.size != dataDimensions.size) || (expectedDimensions.zip(dataDimensions).filterNot(_._1 == -1).exists(_!=_)) then
      throw new RuntimeException(format)
    else
      flattenMultidimensionalArray(value)

  end getArrayItems

  def encodeData(primaryType: String, data: Map[String, Json]):Array[Byte] = ???

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

  def structuredData: Array[Byte] =
    val prefix = "\u0019\u0001".getBytes
    val domainHash = hashDomain
    val messageHash   = hashMessage(messageObject.primaryType, messageObject.message)
    prefix ++ domainHash ++ messageHash
  end structuredData

  def hashStructuredData: Array[Byte] = sha3(structuredData)
end StructuredDataEncoder
