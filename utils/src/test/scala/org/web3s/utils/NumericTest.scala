package org.web3s.utils

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.exceptions.{MessageDecodingException, MessageEncodingException}
import org.web3s.utils.Numeric.asByte


class NumericTest extends AnyFunSuite :

  private val HEX_RANGE_ARRAY = Array[Byte](asByte(0x0, 0x1), asByte(0x2, 0x3), asByte(0x4, 0x5), asByte(0x6, 0x7), asByte(0x8, 0x9), asByte(0xa, 0xb), asByte(0xc, 0xd), asByte(0xe, 0xf))
  private val HEX_RANGE_STRING = "0x0123456789abcdef"

  test("QuantityEncodeLeadingZero") {
    assert(Numeric.toHexStringWithPrefixSafe(BigInt(0L)) == "0x00")
    assert(Numeric.toHexStringWithPrefixSafe(BigInt(1024L)) == "0x400")
    assert(Numeric.toHexStringWithPrefixSafe(BigInt(Long.MaxValue)) == "0x7fffffffffffffff")
    assert(Numeric.toHexStringWithPrefixSafe(BigInt("204516877000845695339750056077105398031")) == "0x99dc848b94efc27edfad28def049810f")
  }

  test("QuantityDecode") {
    assert(Numeric.decodeQuantity("0x0") == BigInt(0L))
    assert(Numeric.decodeQuantity("0x400") == BigInt(1024L))
    assert(Numeric.decodeQuantity("0x0") == BigInt(0L))
    assert(Numeric.decodeQuantity("0x7fffffffffffffff") == BigInt(Long.MaxValue))
    assert(Numeric.decodeQuantity("0x99dc848b94efc27edfad28def049810f") == BigInt("204516877000845695339750056077105398031"))
  }

  test("QuantityDecodeLeadingZero") {
    assert(Numeric.decodeQuantity("0x0400") == BigInt(1024L))
    assert(Numeric.decodeQuantity("0x001") == BigInt(1L))
  }

  test("QuantityDecodeMissingPrefix") {
    assertThrows[MessageDecodingException] {
      Numeric.decodeQuantity("ff")
    }
  }

  test("QuantityDecodeLong") {
    assert(Numeric.decodeQuantity("1234") == BigInt(1234))
  }

  test("QuantityDecodeMissingValue") {
    assertThrows[MessageDecodingException] {
      Numeric.decodeQuantity("0x")
    }
  }

  test("QuantityEncode") {
    assert(Numeric.encodeQuantity(BigInt(0)) == "0x0")
    assert(Numeric.encodeQuantity(BigInt(1)) == "0x1")
    assert(Numeric.encodeQuantity(BigInt(1024)) == "0x400")
    assert(Numeric.encodeQuantity(BigInt(Long.MaxValue)) == "0x7fffffffffffffff")
    assert(Numeric.encodeQuantity(BigInt("204516877000845695339750056077105398031")) == "0x99dc848b94efc27edfad28def049810f")
  }

  test("QuantityEncodeNegative") {
    assertThrows[MessageEncodingException]{
      Numeric.encodeQuantity(BigInt(-1))
    }
  }

  test("CleanHexPrefix") {
    assert(Numeric.cleanHexPrefix("") == "")
    assert(Numeric.cleanHexPrefix("0123456789abcdef") == "0123456789abcdef")
    assert(Numeric.cleanHexPrefix("0x") == "")
    assert(Numeric.cleanHexPrefix("0x0123456789abcdef") == "0123456789abcdef")
  }

  test("PrependHexPrefix") {
    assert(Numeric.prependHexPrefix("") == "0x")
    assert(Numeric.prependHexPrefix("0x0123456789abcdef") == "0x0123456789abcdef")
    assert(Numeric.prependHexPrefix("0x") == "0x")
    assert(Numeric.prependHexPrefix("0123456789abcdef") == "0x0123456789abcdef")
  }

  test("ToHexStringWithPrefix") {
    assert(Numeric.toHexStringWithPrefix(BigInt(10)) == "0xa")
  }

  test("ToHexStringNoPrefix") {
    assert(Numeric.toHexStringNoPrefix(BigInt(10)) == "a")
  }

  test("ToBytesPadded") {
    assert(Numeric.toBytesPadded(BigInt(10), 1) sameElements Array[Byte](0xa))
    assert(Numeric.toBytesPadded(BigInt(10), 8) sameElements Array[Byte](0, 0, 0, 0, 0, 0, 0, 0xa))
    assert(Numeric.toBytesPadded(BigInt(Int.MaxValue), 4) sameElements Array[Byte](0x7f, 0xff.toByte, 0xff.toByte, 0xff.toByte))
  }

  test("ToBytesPaddedInvalid") {
    assertThrows[RuntimeException] {
      Numeric.toBytesPadded(BigInt(Long.MaxValue), 7)
    }
  }

  test("HexStringToByteArray") {
    assert(Numeric.hexStringToByteArray("") sameElements Array.emptyByteArray)
    assert(Numeric.hexStringToByteArray("0") sameElements Array[Byte](0))
    assert(Numeric.hexStringToByteArray("1") sameElements Array[Byte](0x1))
    assert(Numeric.hexStringToByteArray(HEX_RANGE_STRING) sameElements HEX_RANGE_ARRAY)
    assert(Numeric.hexStringToByteArray("0x123") sameElements Array[Byte](0x1, 0x23))
  }

  test("ToHexString") {
    assert(Numeric.toHexString(Array.emptyByteArray) == "0x")
    assert(Numeric.toHexString(Array[Byte](0x1)) == "0x01")
    assert(Numeric.toHexString(HEX_RANGE_ARRAY) == HEX_RANGE_STRING)
  }

  test("ToHexStringNoPrefixZeroPadded") {
    assert(Numeric.toHexStringNoPrefixZeroPadded(BigInt(0), 5) == "00000")
    assert(Numeric.toHexStringNoPrefixZeroPadded(BigInt("11c52b08330e05d731e38c856c1043288f7d9744", 16), 40) == "11c52b08330e05d731e38c856c1043288f7d9744")
    assert(Numeric.toHexStringNoPrefixZeroPadded(BigInt("01c52b08330e05d731e38c856c1043288f7d9744", 16), 40) == "01c52b08330e05d731e38c856c1043288f7d9744")
  }

  test("ToHexStringWithPrefixZeroPadded") {
    assert(Numeric.toHexStringWithPrefixZeroPadded(BigInt(0), 5) == "0x00000")
    assert(Numeric.toHexStringWithPrefixZeroPadded(BigInt("01c52b08330e05d731e38c856c1043288f7d9744", 16), 40) == "0x01c52b08330e05d731e38c856c1043288f7d9744")
    assert(Numeric.toHexStringWithPrefixZeroPadded(BigInt("01c52b08330e05d731e38c856c1043288f7d9744", 16), 40) == "0x01c52b08330e05d731e38c856c1043288f7d9744")
  }

  test("ToHexStringZeroPaddedNegative") {
    assertThrows[UnsupportedOperationException] {
      Numeric.toHexStringNoPrefixZeroPadded(BigInt(-1), 20)
    }
  }

  test("ToHexStringZeroPaddedTooLargs") {
    assertThrows[UnsupportedOperationException] {
      Numeric.toHexStringNoPrefixZeroPadded(BigInt(-1), 5)
    }
  }
  test("IsIntegerValue") {
    assert(Numeric.isIntegerValue(BigDecimal.valueOf(0)))
    assert(Numeric.isIntegerValue(BigDecimal.valueOf(0L)))
    assert(Numeric.isIntegerValue(BigDecimal.valueOf(Long.MaxValue)))
    assert(Numeric.isIntegerValue(BigDecimal.valueOf(Long.MinValue)))
    assert(Numeric.isIntegerValue(BigDecimal("9999999999999999999999999999999999999999999999999999999999999999.0")))
    assert(Numeric.isIntegerValue(BigDecimal("-9999999999999999999999999999999999999999999999999999999999999999.0")))
    assert(!Numeric.isIntegerValue(BigDecimal.valueOf(0.1)))
    assert(!Numeric.isIntegerValue(BigDecimal.valueOf(-0.1)))
    assert(!Numeric.isIntegerValue(BigDecimal.valueOf(1.1)))
    assert(!Numeric.isIntegerValue(BigDecimal.valueOf(-1.1)))
  }

  test("HandleNPE") {
   // assert(!Numeric.containsHexPrefix(null))
    assert(!Numeric.containsHexPrefix(""))
  }

end NumericTest
