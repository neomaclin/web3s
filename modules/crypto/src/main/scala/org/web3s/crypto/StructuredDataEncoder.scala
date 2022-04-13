package org.web3s.crypto

import io.circe.Json.JString
import org.web3s.crypto.Hash.sha3String
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


class StructuredDataEncoder(jsonMessageInString: String): // Parse String Message into object and validate
  
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

  private def convertToBigInt(value: io.circe.Json): BigInt =
    if value.isString then
      value.asString.map(v => if v.startsWith("0x") then Numeric.toBigInt(v) else BigInt(v)).getOrElse(BigInt(0))
    else BigInt(0)
  end convertToBigInt

  
  def getArrayDimensionsFromDeclaration(declaration: String): List[Int] =  // Get the dimensions which were declared in Schema.
     declaration match
       case StructuredDataEncoder.arrayTypeRegex(_, StructuredDataEncoder.arrayDimensionRegex(entries*)) =>
         entries.map(entry => Try(entry.toInt).getOrElse(-1)).toList
       case _ => Nil
     end match
  end getArrayDimensionsFromDeclaration

  def getDepthsAndDimensions[T:Typeable](data: T, depth: Int): String = {
     data match
       case data: List[_] =>
         val list = data.map(getDepthsAndDimensions(_, depth + 1))
         s"${list.mkString("[", ",", "]")}"
       case _ => ""
    }
  ////
  ////  @throws[RuntimeException]
//    def getArrayDimensionsFromData[T](data: T) = {
//      val depthsAndDimensions = getDepthsAndDimensions(data, 0)
//      // groupedByDepth has key as depth and value as List(pair(Depth, Dimension))
//      val groupedByDepth = depthsAndDimensions.stream.collect(Collectors.groupingBy(Pair.getFirst))
//      // depthDimensionsMap is aimed to have key as depth and value as List(Dimension)
//      val depthDimensionsMap = new util.HashMap[Integer, util.List[Integer]]
//      import scala.collection.JavaConversions._
//      for (entry <- groupedByDepth.entrySet) {
//        val pureDimensions = new util.ArrayList[Integer]
//        import scala.collection.JavaConversions._
//        for (depthDimensionPair <- entry.getValue) {
//          pureDimensions.add(depthDimensionPair.getSecond.asInstanceOf[Integer])
//        }
//        depthDimensionsMap.put(entry.getKey.asInstanceOf[Integer], pureDimensions)
//      }
//      val dimensions = new util.ArrayList[Integer]
//      import scala.collection.JavaConversions._
//      for (entry <- depthDimensionsMap.entrySet) {
//        val setOfDimensionsInParticularDepth = new util.TreeSet[Integer](entry.getValue)
//        if (setOfDimensionsInParticularDepth.size != 1) throw new RuntimeException(String.format("Depth %d of array data has more than one dimensions", entry.getKey))
//        dimensions.add(setOfDimensionsInParticularDepth.stream.findFirst.get)
//      }
//      dimensions
//    }
  ////
  ////  def flattenMultidimensionalArray(data: Any): util.List[AnyRef] = {
  ////    if (!data.isInstanceOf[util.List[_]]) return new util.ArrayList[AnyRef]() {}
  ////    val flattenedArray = new util.ArrayList[AnyRef]
  ////    import scala.collection.JavaConversions._
  ////    for (arrayItem <- data.asInstanceOf[util.List[_]]) {
  ////      flattenedArray.addAll(flattenMultidimensionalArray(arrayItem))
  ////    }
  ////    flattenedArray
  ////  }
  ////
  ////  private def convertToEncodedItem(baseType: String, data: Any) = {
  ////    var hashBytes = null
  ////    try if (baseType.toLowerCase.startsWith("uint") || baseType.toLowerCase.startsWith("int")) {
  ////      val value = convertToBigInt(data)
  ////      if (value.signum >= 0) hashBytes = Numeric.toBytesPadded(convertToBigInt(data), MAX_BYTE_LENGTH)
  ////      else {
  ////        val signPadding = 0xff.toByte
  ////        val rawValue = convertToBigInt(data).toByteArray
  ////        hashBytes = new Array[Byte](MAX_BYTE_LENGTH)
  ////        for (i <- 0 until hashBytes.length) {
  ////          hashBytes(i) = signPadding
  ////        }
  ////        System.arraycopy(rawValue, 0, hashBytes, MAX_BYTE_LENGTH - rawValue.length, rawValue.length)
  ////      }
  ////    }
  ////    else if (baseType == "string") hashBytes = data.asInstanceOf[String].getBytes
  ////    else if (baseType == "bytes") hashBytes = Numeric.hexStringToByteArray(data.asInstanceOf[String])
  ////    else {
  ////      val b = StructuredDataEncoder.convertArgToBytes(data.asInstanceOf[String])
  ////      val bi = new BigInteger(1, b)
  ////      hashBytes = Numeric.toBytesPadded(bi, 32)
  ////    }
  ////    catch {
  ////      case e: Exception =>
  ////        e.printStackTrace()
  ////        hashBytes = new Array[Byte](0)
  ////    }
  ////    hashBytes
  ////  }
  ////
  ////  private def getArrayItems(field: StructuredData.Entry, value: Any) = {
  ////    val expectedDimensions = getArrayDimensionsFromDeclaration(field.getType)
  ////    // This function will itself give out errors in case
  ////    // that the data is not a proper array
  ////    val dataDimensions = getArrayDimensionsFromData(value)
  ////    val format = String.format("Array Data %s has dimensions %s, " + "but expected dimensions are %s", value.toString, dataDimensions.toString, expectedDimensions.toString)
  ////    if (expectedDimensions.size != dataDimensions.size) { // Ex: Expected a 3d array, but got only a 2d array
  ////      throw new RuntimeException(format)
  ////    }
  ////    for (i <- 0 until expectedDimensions.size) {
  ////      if (expectedDimensions.get(i) eq -1) { // Skip empty or dynamically declared dimensions
  ////        continue //todo: continue is not supported
  ////      }
  ////      if (!(expectedDimensions.get(i) == dataDimensions.get(i))) throw new RuntimeException(format)
  ////    }
  ////    flattenMultidimensionalArray(value)
  ////  }
  ////
  ////  @SuppressWarnings(Array("unchecked"))
  ////  @throws[RuntimeException]
  ////  def encodeData(primaryType: String, data: util.HashMap[String, AnyRef]) = {
  ////    val types = jsonMessageObject.getTypes
  ////    val encTypes = new util.ArrayList[String]
  ////    val encValues = new util.ArrayList[AnyRef]
  ////    // Add typehash
  ////    encTypes.add("bytes32")
  ////    encValues.add(typeHash(primaryType))
  ////    // Add field contents
  ////    import scala.collection.JavaConversions._
  ////    for (field <- types.get(primaryType)) {
  ////      val value = data.get(field.getName)
  ////      if (value == null) continue //todo: continue is not supported
  ////      if (field.getType.equals("string")) {
  ////        encTypes.add("bytes32")
  ////        val hashedValue = Numeric.hexStringToByteArray(sha3String(value.asInstanceOf[String]))
  ////        encValues.add(hashedValue)
  ////      }
  ////      else if (field.getType.equals("bytes")) {
  ////        encTypes.add("bytes32")
  ////        encValues.add(sha3(Numeric.hexStringToByteArray(value.asInstanceOf[String])))
  ////      }
  ////      else if (types.containsKey(field.getType)) { // User Defined Type
  ////        val hashedValue = sha3(encodeData(field.getType, value.asInstanceOf[util.HashMap[String, AnyRef]]))
  ////        encTypes.add("bytes32")
  ////        encValues.add(hashedValue)
  ////      }
  ////      else if (bytesTypePattern.matcher(field.getType).find) {
  ////        encTypes.add(field.getType)
  ////        encValues.add(Numeric.hexStringToByteArray(value.asInstanceOf[String]))
  ////      }
  ////      else if (arrayTypePattern.matcher(field.getType).find) {
  ////        val baseTypeName = field.getType.substring(0, field.getType.indexOf('['))
  ////        val arrayItems = getArrayItems(field, value)
  ////        val concatenatedArrayEncodingBuffer = new ByteArrayOutputStream
  ////        import scala.collection.JavaConversions._
  ////        for (arrayItem <- arrayItems) {
  ////          var arrayItemEncoding = null
  ////          if (types.containsKey(baseTypeName)) {
  ////            arrayItemEncoding = sha3(encodeData(baseTypeName, arrayItem.asInstanceOf[util.HashMap[String, AnyRef]])) // need to hash each user type
  ////
  ////            // before adding
  ////          }
  ////          else arrayItemEncoding = convertToEncodedItem(baseTypeName, arrayItem) // add raw item, packed to 32 bytes
  ////          concatenatedArrayEncodingBuffer.write(arrayItemEncoding, 0, arrayItemEncoding.length)
  ////        }
  ////        val concatenatedArrayEncodings = concatenatedArrayEncodingBuffer.toByteArray
  ////        val hashedValue = sha3(concatenatedArrayEncodings)
  ////        encTypes.add("bytes32")
  ////        encValues.add(hashedValue)
  ////      }
  ////      else if (field.getType.startsWith("uint") || field.getType.startsWith("int")) {
  ////        encTypes.add(field.getType)
  ////        // convert to BigInteger for ABI constructor compatibility
  ////        try encValues.add(convertToBigInt(value))
  ////        catch {
  ////          case e@(_: NumberFormatException | _: NullPointerException) =>
  ////            encValues.add(value) // value null or failed to convert, fallback to add string as
  ////
  ////          // before
  ////        }
  ////      }
  ////      else {
  ////        encTypes.add(field.getType)
  ////        encValues.add(value)
  ////      }
  ////    }
  ////    val baos = new ByteArrayOutputStream
  ////    for (i <- 0 until encTypes.size) {
  ////      val typeClazz = AbiTypes.getType(encTypes.get(i)).asInstanceOf[Class[Nothing]]
  ////      var atleastOneConstructorExistsForGivenParametersType = false
  ////      // Using the Reflection API to get the types of the parameters
  ////      val constructors = typeClazz.getConstructors
  ////      for (constructor <- constructors) { // Check which constructor matches
  ////        try {
  ////          val parameterTypes = constructor.getParameterTypes
  ////          val temp = Numeric.hexStringToByteArray(TypeEncoder.encode(typeClazz.getDeclaredConstructor(parameterTypes).newInstance(encValues.get(i))))
  ////          baos.write(temp, 0, temp.length)
  ////          atleastOneConstructorExistsForGivenParametersType = true
  ////          break //todo: break is not supported
  ////        } catch {
  ////          case ignored@(_: IllegalArgumentException | _: NoSuchMethodException | _: InstantiationException | _: IllegalAccessException | _: InvocationTargetException) =>
  ////        }
  ////      }
  ////      if (!atleastOneConstructorExistsForGivenParametersType) throw new RuntimeException(String.format("Received an invalid argument for which no constructor" + " exists for the ABI Class %s", typeClazz.getSimpleName))
  ////    }
  ////    baos.toByteArray
  ////  }
  ////


  ////
  ////  @throws[RuntimeException]
  ////  def hashMessage(primaryType: String, data: util.HashMap[String, AnyRef]) = sha3(encodeData(primaryType, data))
  ////
  ////  @SuppressWarnings(Array("unchecked"))
  ////  @throws[RuntimeException]
  ////  def hashDomain = {
  ////    val oMapper = new Nothing
  ////    val data = oMapper.convertValue(jsonMessageObject.getDomain, classOf[util.HashMap[_, _]])
  ////    if (data.get("chainId") != null) data.put("chainId", data.get("chainId").asInstanceOf[util.HashMap[String, AnyRef]].get("value"))
  ////    else data.remove("chainId")
  ////    if (data.get("verifyingContract") != null) data.put("verifyingContract", data.get("verifyingContract").asInstanceOf[util.HashMap[String, AnyRef]].get("value"))
  ////    else data.remove("verifyingContract")
  ////    if (data.get("salt") == null) data.remove("salt")
  ////    sha3(encodeData("EIP712Domain", data))
  ////  }
  ////
  ////  @throws[RuntimeException]
  ////  def validateStructuredData(jsonMessageObject: StructuredData.EIP712Message) = {
  ////    import scala.collection.JavaConversions._
  ////    for (structName <- jsonMessageObject.getTypes.keySet) {
  ////      val fields = jsonMessageObject.getTypes.get(structName)
  ////      import scala.collection.JavaConversions._
  ////      for (entry <- fields) {
  ////        if (!identifierPattern.matcher(entry.getName).find) { // raise Error
  ////          throw new RuntimeException(String.format("Invalid Identifier %s in %s", entry.getName, structName))
  ////        }
  ////        if (!typePattern.matcher(entry.getType).find) throw new RuntimeException(String.format("Invalid Type %s in %s", entry.getType, structName))
  ////      }
  ////    }
  ////  }
  ////
  //
  //////  @throws[IOException]
  //////  @throws[RuntimeException]
  //////  def parseJSONMessage(jsonMessageInString: String) = {
  //////    decode[StructuredData.EIP712Message](jsonMessageInString)
  //////  }
  ////
  ////
  ////  @SuppressWarnings(Array("unchecked"))
  ////  @throws[RuntimeException]
  ////  def getStructuredData = {
  ////    val baos = new ByteArrayOutputStream
  ////    val messagePrefix = "\u0019\u0001"
  ////    val prefix = messagePrefix.getBytes
  ////    baos.write(prefix, 0, prefix.length)
  ////    val domainHash = hashDomain
  ////    baos.write(domainHash, 0, domainHash.length)
  ////    val dataHash = hashMessage(jsonMessageObject.getPrimaryType, jsonMessageObject.getMessage.asInstanceOf[util.HashMap[String, AnyRef]])
  ////    baos.write(dataHash, 0, dataHash.length)
  ////    baos.toByteArray
  ////  }
  ////
  ////  @SuppressWarnings(Array("unchecked"))
  ////  @throws[RuntimeException]
  ////  def hashStructuredData = sha3(getStructuredData)
end StructuredDataEncoder
