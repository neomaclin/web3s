
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric

class StructuredDataTest extends AnyFunSuite :

  private val validStructuredDataJSONFilePath = "structured_data_json_files/ValidStructuredData.json"
  private val jsonMessageString = scala.io.Source.fromResource(validStructuredDataJSONFilePath).getLines().mkString

  test("InvalidIdentifierMessageCaughtByRegex") {
    val invalidStructuredDataJSONFilePath = "structured_data_json_files/InvalidIdentifierStructuredData.json"
    assertThrows[Exception] {
      new StructuredDataEncoder(scala.io.Source.fromResource(invalidStructuredDataJSONFilePath).getLines().mkString)
    }
  }

  test("InvalidTypeMessageCaughtByRegex") {
    val invalidStructuredDataJSONFilePath = "structured_data_json_files/InvalidTypeStructuredData.json"
    assertThrows[Exception] {
      new StructuredDataEncoder(scala.io.Source.fromResource(invalidStructuredDataJSONFilePath).getLines().mkString)
    }
  }

  test("GetDependencies") {
    val dataEncoder = new StructuredDataEncoder(jsonMessageString)
    val deps = dataEncoder.getDependencies(dataEncoder.messageObject.primaryType)
    val depsExpected = Set("Mail", "Person")
    assert(deps equals depsExpected)
  }
  //
  test("EncodeType") {
    val dataEncoder = new StructuredDataEncoder(jsonMessageString)
    val expectedTypeEncoding = "Mail(Person from,Person to,string contents)" + "Person(string name,address wallet)"
    val encoding = dataEncoder.encodeType(dataEncoder.messageObject.primaryType)
    assert(encoding == expectedTypeEncoding)
  }

  test("TypeHash") {
    val dataEncoder = new StructuredDataEncoder(jsonMessageString)
    val expectedTypeHashHex = "0xa0cedeb2dc280ba39b857546d74f5549c" + "3a1d7bdc2dd96bf881f76108e23dac2"
    assert(Numeric.toHexString(dataEncoder.typeHash(dataEncoder.messageObject.primaryType)) == expectedTypeHashHex)
  }

  //def testEncodeData() = {
  //  val dataEncoder = new StructuredDataEncoder(StructuredDataTest.jsonMessageString)
  //  val encodedData = dataEncoder.encodeData(dataEncoder.jsonMessageObject.getPrimaryType, dataEncoder.jsonMessageObject.getMessage.asInstanceOf[util.HashMap[String, AnyRef]])
  //  val expectedDataEncodingHex = "0xa0cedeb2dc280ba39b857546d74f5549c3a1d7bd" + "c2dd96bf881f76108e23dac2fc71e5fa27ff56c350aa531bc129ebdf613b772b6" + "604664f5d8dbe21b85eb0c8cd54f074a4af31b4411ff6a60c9719dbd559c221c8" + "ac3492d9d872b041d703d1b5aadf3154a261abdd9086fc627b61efca26ae57027" + "01d05cd2305f7c52a2fc8"
  //  assertEquals(Numeric.toHexString(encodedData), expectedDataEncodingHex)
  //}
  //
  //def testHashData() = {
  //  val dataEncoder = new StructuredDataEncoder(StructuredDataTest.jsonMessageString)
  //  val dataHash = dataEncoder.hashMessage(dataEncoder.jsonMessageObject.getPrimaryType, dataEncoder.jsonMessageObject.getMessage.asInstanceOf[util.HashMap[String, AnyRef]])
  //  val expectedMessageStructHash = "0xc52c0ee5d84264471806290a3f2c4cecf" + "c5490626bf912d01f240d7a274b371e"
  //  assertEquals(Numeric.toHexString(dataHash), expectedMessageStructHash)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testHashDomain() = {
  //  val dataEncoder = new StructuredDataEncoder(StructuredDataTest.jsonMessageString)
  //  val structHashDomain = dataEncoder.hashDomain
  //  val expectedDomainStructHash = "0xf2cee375fa42b42143804025fc449deafd" + "50cc031ca257e0b194a650a912090f"
  //  assertEquals(Numeric.toHexString(structHashDomain), expectedDomainStructHash)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testHashStructuredMessage() = {
  //  val dataEncoder = new StructuredDataEncoder(StructuredDataTest.jsonMessageString)
  //  val hashStructuredMessage = dataEncoder.hashStructuredData
  //  val expectedDomainStructHash = "0xbe609aee343fb3c4b28e1df9e632fca64fcfaede20" + "f02e86244efddf30957bd2"
  //  assertEquals(Numeric.toHexString(hashStructuredMessage), expectedDomainStructHash)
  //}
  //
  //@Test
  //@throws[IOException]
  //def testBytesAndBytes32Encoding() = {
  //  val dataEncoder = new StructuredDataEncoder(getResource("build/resources/test/" + "structured_data_json_files/ValidStructuredDataWithBytesTypes.json"))
  //  dataEncoder.hashStructuredData
  //}
  //
  //@Test
  //@throws[IOException]
  //def test0xProtocolControlSample() = {
  //  val dataEncoder = new StructuredDataEncoder(getResource("build/resources/test/" + "structured_data_json_files/0xProtocolControlSample.json"))
  //  assertEquals("0xccb29124860915763e8cd9257da1260abc7df668fde282272587d84b594f37f6", Numeric.toHexString(dataEncoder.hashStructuredData))
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testInvalidMessageValueTypeMismatch() = {
  //  val invalidStructuredDataJSONFilePath = "build/resources/test/" + "structured_data_json_files/InvalidMessageValueTypeMismatch.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(invalidStructuredDataJSONFilePath))
  //  assertThrows(classOf[ClassCastException], dataEncoder.hashStructuredData)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testInvalidMessageInvalidABIType() = {
  //  val invalidStructuredDataJSONFilePath = "build/resources/test/" + "structured_data_json_files/InvalidMessageInvalidABIType.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(invalidStructuredDataJSONFilePath))
  //  assertThrows(classOf[UnsupportedOperationException], dataEncoder.hashStructuredData)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testInvalidMessageValidABITypeInvalidValue() = {
  //  val invalidStructuredDataJSONFilePath = "build/resources/test/" + "structured_data_json_files/InvalidMessageValidABITypeInvalidValue.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(invalidStructuredDataJSONFilePath))
  //  assertThrows(classOf[RuntimeException], dataEncoder.hashStructuredData)
  //}
  //
  //// EIP712 v3
  //@Test
  //@throws[IOException]
  //def testValidStructureWithValues() = {
  //  val dataEncoder = new StructuredDataEncoder(getResource("build/resources/test/" + "structured_data_json_files/ValidStructuredDataWithValues.json"))
  //  assertEquals("0x9a321bee2df12b3b43bc4caf71d19839f05d82264b780b48f1f529bf916b5d30", Numeric.toHexString(dataEncoder.hashStructuredData))
  //}
  //
  //// EIP712 v4
  //@Test
  //@throws[IOException]
  //def testValidStructureWithArrays() = {
  //  val dataEncoder = new StructuredDataEncoder(getResource("build/resources/test/" + "structured_data_json_files/ValidStructuredArrayData.json")) // taken from https://danfinlay.github.io/js-eth-personal-sign-examples/ and updated to int,uint arrays
  //  val dataHash = dataEncoder.hashMessage(dataEncoder.jsonMessageObject.getPrimaryType, dataEncoder.jsonMessageObject.getMessage.asInstanceOf[util.HashMap[String, AnyRef]])
  //  val expectedMessageStructHash = "0xc1c7d7b7dab9a65b30a6e951923b2d54536778329712e2239ed8a3f2f5f2329f"
  //  assertEquals(expectedMessageStructHash, Numeric.toHexString(dataHash))
  //  assertEquals("0x935426a6009a3798ee87cd16ebeb9cea26b29d2d3762ac0951166d032f55d522", Numeric.toHexString(dataEncoder.hashStructuredData))
  //}
  //
  //// EIP712 v3 Gnosis format; Multisig Structured message with addresses blanked
  //@Test
  //@throws[IOException]
  //def testValidGnosisStructure() = {
  //  val dataEncoder = new StructuredDataEncoder(getResource("build/resources/test/" + "structured_data_json_files/ValidStructuredGnosisData.json")) // typical Gnosis safe EIP712 data
  //  assertEquals("0xb3ffe0073b0c3aecb00b19a636e4b3820cfc9692189f874312c906f70edf10bd", Numeric.toHexString(dataEncoder.hashStructuredData))
  //}
  //

  test("GetArrayDimensionsFromData") {
    val dataEncoder = new StructuredDataEncoder(jsonMessageString)

    // [[1, 2, 3], [4, 5, 6]]
    val testArrayData1 = List(List(1, 2, 3), List(4, 5, 6))
    val expectedDimensions1 = List(2, 3)
    assert(dataEncoder.getArrayDimensionsFromData(testArrayData1) sameElements expectedDimensions1)

    // [[1, 2, 3]]
    val testArrayData2 = List(List(1, 2, 3))
    val expectedDimensions2 = List(1, 3)
    assert(dataEncoder.getArrayDimensionsFromData(testArrayData2) == expectedDimensions2)

    // [1, 2, 3]
    val testArrayData3 = List(1, 2, 3)
    val expectedDimensions3 = List(3)
    assert(dataEncoder.getArrayDimensionsFromData(testArrayData3) == expectedDimensions3)

    // [[[1, 2], [3, 4], [5, 6]], [[7, 8], [9, 10], [11, 12]]]
    val testArrayData4 = List(List(List(1, 2), List(3,4), List(5,6)), List(List(7,8), List(9,10), List(11,12)))
    val expectedDimensions4 = List(2,3,2)
    assert(dataEncoder.getArrayDimensionsFromData(testArrayData4) == expectedDimensions4)

  }

   test("FlattenMultidimensionalArray")  {
     val dataEncoder = new StructuredDataEncoder(jsonMessageString)

     // [[1, 2, 3], [4, 5, 6]]
     val testArrayData1 = List(List(1, 2, 3), List(4, 5, 6))
     val testArrayData1Flatten = testArrayData1.flatten
     assert(dataEncoder.flattenMultidimensionalArray(testArrayData1) == testArrayData1Flatten)

     // [[1, 2, 3]]
     val testArrayData2 = List(List(1, 2, 3))
     val testArrayData2Flatten = testArrayData2.flatten
     assert(dataEncoder.flattenMultidimensionalArray(testArrayData2) == testArrayData2Flatten)

     // [1, 2, 3]
     val testArrayData3 = List(1, 2, 3)
     val testArrayData3Flatten = testArrayData3
     assert(dataEncoder.flattenMultidimensionalArray(testArrayData3) == testArrayData3Flatten)

     // [[[1, 2], [3, 4], [5, 6]], [[7, 8], [9, 10], [11, 12]]]
     val testArrayData4 = List(List(List(1, 2), List(3,4), List(5,6)), List(List(7,8), List(9,10), List(11,12)))
     val testArrayData4Flatten = testArrayData4.flatten.flatten
     assert(dataEncoder.flattenMultidimensionalArray(testArrayData4) == testArrayData4Flatten)
  }
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testUnequalArrayLengthsBetweenSchemaAndData() = {
  //  val invalidStructuredDataJSONFilePath = "build/resources/test/" + "structured_data_json_files/" + "InvalidMessageUnequalArrayLengthsBetweenSchemaAndData.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(invalidStructuredDataJSONFilePath))
  //  assertThrows(classOf[RuntimeException], dataEncoder.hashStructuredData)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testDataNotPerfectArrayButDeclaredArrayInSchema() = {
  //  val invalidStructuredDataJSONFilePath = "build/resources/test/" + "structured_data_json_files/" + "InvalidMessageDataNotPerfectArrayButDeclaredArrayInSchema.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(invalidStructuredDataJSONFilePath))
  //  assertThrows(classOf[RuntimeException], dataEncoder.hashStructuredData)
  //}
  //
  //@Test
  //@throws[RuntimeException]
  //@throws[IOException]
  //def testHashDomainWithSalt() = {
  //  val validStructuredDataWithSaltJSONFilePath = "build/resources/test/" + "structured_data_json_files/" + "ValidStructuredDataWithSalt.json"
  //  val dataEncoder = new StructuredDataEncoder(getResource(validStructuredDataWithSaltJSONFilePath))
  //  val structHashDomain = dataEncoder.hashDomain
  //  val expectedDomainStructHash = "0xbae4f6f7b9bfdfda060692099b0e1ccecd25d62b7c92cc9f3b907f33178b81e3"
  //  assertEquals(Numeric.toHexString(structHashDomain), expectedDomainStructHash)
  //}

end StructuredDataTest
