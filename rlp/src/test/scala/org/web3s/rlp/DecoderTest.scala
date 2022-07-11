
package org.web3s.rlp

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric


class DecoderTest extends AnyFunSuite :

 test("Decode") {
     // big positive number should stay positive after encoding-decoding
      // https://github.com/web3j/web3j/issues/562
      val value = 3000000000L
      assert(RlpString.from(BigInt(value)).asPositiveBigInt.longValue == value)
      // empty array of binary
      assert(Decoder.decode(Array.emptyByteArray).values.isEmpty)
      // The string "dog" = [ 0x83, 'd', 'o', 'g' ]
      assert(Decoder.decode(Array[Byte](0x83.toByte, 'd', 'o', 'g')).values(0) === RlpString.from("dog"))
      // The list [ "cat", "dog" ] = [ 0xc8, 0x83, 'c', 'a', 't', 0x83, 'd', 'o', 'g' ]
      var rlpList = Decoder.decode(Array[Byte](0xc8.toByte, 0x83.toByte, 'c', 'a', 't', 0x83.toByte, 'd', 'o', 'g')).values(0).asInstanceOf[RlpList]
      assert(rlpList.values(0) == RlpString.from("cat"))
      assert(rlpList.values(1) == RlpString.from("dog"))
      // The empty string ('null') = [ 0x80 ]
      assert(Decoder.decode(Array[Byte](0x80.toByte)).values(0) == RlpString.from(""))
      assert(Decoder.decode(Array[Byte](0x80.toByte)).values(0) == RlpString(Array.emptyByteArray))
      assert(Decoder.decode(Array[Byte](0x80.toByte)).values(0) == RlpString.from(BigInt(0)))
//      // The empty list = [ 0xc0 ]
      assert(Decoder.decode(Array[Byte](0xc0.toByte)).values(0).getClass == classOf[RlpList])
      assert(Decoder.decode(Array[Byte](0xc0.toByte)).values(0).asInstanceOf[RlpList].values.isEmpty)
      // The encoded integer 0 ('\x00') = [ 0x00 ]
      assert(Decoder.decode(Array[Byte](0x00.toByte)).values(0) == RlpString.from(BigInt(0).byteValue))
      // The encoded integer 15 ('\x0f') = [ 0x0f ]
      assert(Decoder.decode(Array[Byte](0x0f.toByte)).values(0) == RlpString.from(BigInt(15).byteValue))
      // The encoded integer 1024 ('\x04\x00') = [ 0x82, 0x04, 0x00 ]
      assert(Decoder.decode(Array[Byte](0x82.toByte, 0x04.toByte, 0x00.toByte)).values(0) == RlpString.from(BigInt(0x0400)))
      // The set theoretical representation of three,
      // [ [], [[]], [ [], [[]] ] ] = [ 0xc7, 0xc0, 0xc1, 0xc0, 0xc3, 0xc0, 0xc1, 0xc0 ]
      rlpList = Decoder.decode(Array[Byte](0xc7.toByte, 0xc0.toByte, 0xc1.toByte, 0xc0.toByte, 0xc3.toByte, 0xc0.toByte, 0xc1.toByte, 0xc0.toByte))
      assert(rlpList.getClass == classOf[RlpList])
      assert(rlpList.values.size == 1)
      assert(rlpList.values(0).getClass == classOf[RlpList])
      assert(rlpList.values(0).asInstanceOf[RlpList].values.size == 3)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(0).getClass == classOf[RlpList])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(0).asInstanceOf[RlpList].values.size == 0)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(1).asInstanceOf[RlpList].values.size == 1)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2).asInstanceOf[RlpList].values.size == 2)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2).asInstanceOf[RlpList].values(0).getClass == classOf[RlpList])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2).asInstanceOf[RlpList].values(0).asInstanceOf[RlpList].values.size == 0)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2).asInstanceOf[RlpList].values(1).asInstanceOf[RlpList].values.size == 1)
      // The string "Lorem ipsum dolor sit amet,
      // consectetur adipisicing elit" =
      // [ 0xb8, 0x38, 'L', 'o', 'r', 'e', 'm', ' ', ... , 'e', 'l', 'i', 't' ]
      assert(Decoder.decode(Array[Byte](0xb8.toByte, 0x38.toByte, 'L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm', ' ', 'd', 'o', 'l', 'o', 'r', ' ', 's', 'i', 't', ' ', 'a', 'm', 'e', 't', ',', ' ', 'c', 'o', 'n', 's', 'e', 'c', 't', 'e', 't', 'u', 'r', ' ', 'a', 'd', 'i', 'p', 'i', 's', 'i', 'c', 'i', 'n', 'g', ' ', 'e', 'l', 'i', 't')).values(0) == RlpString.from("Lorem ipsum dolor sit amet, consectetur adipisicing elit"))
      // https://github.com/paritytech/parity/blob/master/util/rlp/tests/tests.rs#L239
      assert(Decoder.decode(Array[Byte](0x00.toByte)).values(0) == RlpString(Array[Byte](0)))
      rlpList = Decoder.decode(Array[Byte](0xc6.toByte, 0x82.toByte, 0x7a.toByte, 0x77.toByte, 0xc1.toByte, 0x04.toByte, 0x01.toByte))
      assert(rlpList.values(0).asInstanceOf[RlpList].values.size == 3)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(0).getClass == classOf[RlpString])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(1).getClass == classOf[RlpList])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2).getClass == classOf[RlpString])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(0) == RlpString.from("zw"))
      assert(rlpList.values(0).asInstanceOf[RlpList].values(1).asInstanceOf[RlpList].values(0) == RlpString.from(4))
      assert(rlpList.values(0).asInstanceOf[RlpList].values(2) == RlpString.from(1))
      // payload more than 55 bytes
      val data = "F86E12F86B80881BC16D674EC8000094CD2A3D9F938E13CD947EC05ABC7FE734D" + "F8DD8268609184E72A00064801BA0C52C114D4F5A3BA904A9B3036E5E118FE0DBB987" + "FE3955DA20F2CD8F6C21AB9CA06BA4C2874299A55AD947DBC98A25EE895AABF6B625C" + "26C435E84BFD70EDF2F69"
      val payload = Numeric.hexStringToByteArray(data)
      rlpList = Decoder.decode(payload)
      assert(rlpList.values(0).asInstanceOf[RlpList].values.size == 2)
      assert(rlpList.values(0).asInstanceOf[RlpList].values(0).getClass == classOf[RlpString])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(1).getClass == classOf[RlpList])
      assert(rlpList.values(0).asInstanceOf[RlpList].values(1).asInstanceOf[RlpList].values.size == 9)
      // Regression test: this would previously throw OutOfMemoryError as it tried to allocate 2GB
      // for the non-existent data
      assertThrows[RuntimeException]{
        Decoder.decode(Array[Byte](0xbb.toByte, 0x7f.toByte, 0xff.toByte, 0xff.toByte, 0xff.toByte))
      }
    }
  
end DecoderTest
