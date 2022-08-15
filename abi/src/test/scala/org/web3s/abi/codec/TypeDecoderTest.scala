package org.web3s.abi.codec

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.codec.decoders.given
import org.web3s.abi.codec.decoders.DecoderMacro

class TypeDecoderTest extends AnyFunSuite :

  test("BoolDecode") {
    assert(TypeDecoder.decode[Bool]("0000000000000000000000000000000000000000000000000000000000000000", 0) == Bool(false))
    assert(TypeDecoder.decode[Bool]("0000000000000000000000000000000000000000000000000000000000000001", 0) == Bool(true))
    //    assert(TypeDecoder.instantiateType("bool", true), new Nothing(true))
    //    assert(TypeDecoder.instantiateType("bool", 1), new Nothing(true))
    //    assert(TypeDecoder.instantiateType("bool", false), new Nothing(false))
    //    assert(TypeDecoder.instantiateType("bool", 0), new Nothing(false))
  }
  ////
  test("BoolDecodeGivenOffset") { // Decode second parameter as Bool
    assert(TypeDecoder.decode[Bool]("0000000000000000000000000000000000000000000000007fffffffffffffff" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 64) == Bool(false))
    assert(TypeDecoder.decode[Bool]("0000000000000000000000000000000000000000000000007fffffffffffffff" + "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 64) == Bool(true))
  }

  test("UintDecode") {

    assert(TypeDecoder.decode[UInt8]("0000000000000000000000000000000000000000000000000000000000000000") == UInt8(BigInt(0)))
    assert(TypeDecoder.decode[UInt8]("00000000000000000000000000000000000000000000000000000000000000ff") == UInt8(BigInt(255)))
    assert(TypeDecoder.decode[UInt16]("0000000000000000000000000000000000000000000000000000000000000000") == UInt16(BigInt(0)))
    assert(TypeDecoder.decode[UInt16]("000000000000000000000000000000000000000000000000000000000000ffff") == UInt16(BigInt(65535)))
    assert(TypeDecoder.decode[UInt24]("0000000000000000000000000000000000000000000000000000000000000000") == UInt24(BigInt(0)))
    assert(TypeDecoder.decode[UInt24]("0000000000000000000000000000000000000000000000000000000000ffffff") == UInt24(BigInt(16777215)))
    assert(TypeDecoder.decode[UInt32]("0000000000000000000000000000000000000000000000000000000000000000") == UInt32(BigInt(0)))
    assert(TypeDecoder.decode[UInt32]("00000000000000000000000000000000000000000000000000000000ffffffff") == UInt32(BigInt(4294967295L)))
    assert(TypeDecoder.decode[UInt40]("0000000000000000000000000000000000000000000000000000000000000000") == UInt40(BigInt(0)))
    assert(TypeDecoder.decode[UInt40]("000000000000000000000000000000000000000000000000000000ffffffffff") == UInt40(BigInt(1099511627775L)))
    assert(TypeDecoder.decode[UInt48]("0000000000000000000000000000000000000000000000000000000000000000") == UInt48(BigInt(0)))
    assert(TypeDecoder.decode[UInt48]("0000000000000000000000000000000000000000000000000000ffffffffffff") == UInt48(BigInt(281474976710655L)))
    assert(TypeDecoder.decode[UInt56]("0000000000000000000000000000000000000000000000000000000000000000") == UInt56(BigInt(0)))
    assert(TypeDecoder.decode[UInt56]("00000000000000000000000000000000000000000000000000ffffffffffffff") == UInt56(BigInt(72057594037927935L)))
    assert(TypeDecoder.decode[UInt64]("0000000000000000000000000000000000000000000000000000000000000000") == UInt64(BigInt(0)))
    assert(TypeDecoder.decode[UInt64]("0000000000000000000000000000000000000000000000007fffffffffffffff") == UInt64(BigInt(Long.MaxValue)))
    assert(TypeDecoder.decode[UInt64]("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt64(BigInt("0ffffffffffffffff", 16)))
    assert(TypeDecoder.decode[UInt72]("0000000000000000000000000000000000000000000000000000000000000000") == UInt72(BigInt(0)))
    assert(TypeDecoder.decode[UInt72]("0000000000000000000000000000000000000000000000ffffffffffffffffff") == UInt72(BigInt("4722366482869645213695")))
    assert(TypeDecoder.decode[UInt80]("0000000000000000000000000000000000000000000000000000000000000000") == UInt80(BigInt(0)))
    assert(TypeDecoder.decode[UInt80]("00000000000000000000000000000000000000000000ffffffffffffffffffff") == UInt80(BigInt("1208925819614629174706175")))
    assert(TypeDecoder.decode[UInt88]("0000000000000000000000000000000000000000000000000000000000000000") == UInt88(BigInt(0)))
    assert(TypeDecoder.decode[UInt88]("000000000000000000000000000000000000000000ffffffffffffffffffffff") == UInt88(BigInt("309485009821345068724781055")))
    assert(TypeDecoder.decode[UInt96]("0000000000000000000000000000000000000000000000000000000000000000") == UInt96(BigInt(0)))
    assert(TypeDecoder.decode[UInt96]("0000000000000000000000000000000000000000ffffffffffffffffffffffff") == UInt96(BigInt("79228162514264337593543950335")))
    assert(TypeDecoder.decode[UInt104]("0000000000000000000000000000000000000000000000000000000000000000") == UInt104(BigInt(0)))
    assert(TypeDecoder.decode[UInt104]("00000000000000000000000000000000000000ffffffffffffffffffffffffff") == UInt104(BigInt("20282409603651670423947251286015")))
    assert(TypeDecoder.decode[UInt112]("0000000000000000000000000000000000000000000000000000000000000000") == UInt112(BigInt(0)))
    assert(TypeDecoder.decode[UInt112]("000000000000000000000000000000000000ffffffffffffffffffffffffffff") == UInt112(BigInt("5192296858534827628530496329220095")))
    assert(TypeDecoder.decode[UInt120]("0000000000000000000000000000000000000000000000000000000000000000") == UInt120(BigInt(0)))
    assert(TypeDecoder.decode[UInt120]("0000000000000000000000000000000000ffffffffffffffffffffffffffffff") == UInt120(BigInt("1329227995784915872903807060280344575")))
    assert(TypeDecoder.decode[UInt128]("0000000000000000000000000000000000000000000000000000000000000000") == UInt128(BigInt(0)))
    assert(TypeDecoder.decode[UInt128]("00000000000000000000000000000000ffffffffffffffffffffffffffffffff") == UInt128(BigInt("340282366920938463463374607431768211455")))
    assert(TypeDecoder.decode[UInt136]("0000000000000000000000000000000000000000000000000000000000000000") == UInt136(BigInt(0)))
    assert(TypeDecoder.decode[UInt136]("000000000000000000000000000000ffffffffffffffffffffffffffffffffff") == UInt136(BigInt("87112285931760246646623899502532662132735")))
    assert(TypeDecoder.decode[UInt144]("0000000000000000000000000000000000000000000000000000000000000000") == UInt144(BigInt(0)))
    assert(TypeDecoder.decode[UInt144]("0000000000000000000000000000ffffffffffffffffffffffffffffffffffff") == UInt144(BigInt("22300745198530623141535718272648361505980415")))
    assert(TypeDecoder.decode[UInt152]("0000000000000000000000000000000000000000000000000000000000000000") == UInt152(BigInt(0)))
    assert(TypeDecoder.decode[UInt152]("00000000000000000000000000ffffffffffffffffffffffffffffffffffffff") == UInt152(BigInt("5708990770823839524233143877797980545530986495")))
    assert(TypeDecoder.decode[UInt160]("0000000000000000000000000000000000000000000000000000000000000000") == UInt160(BigInt(0)))
    assert(TypeDecoder.decode[UInt160]("000000000000000000000000ffffffffffffffffffffffffffffffffffffffff") == UInt160(BigInt("1461501637330902918203684832716283019655932542975")))
    assert(TypeDecoder.decode[UInt168]("0000000000000000000000000000000000000000000000000000000000000000") == UInt168(BigInt(0)))
    assert(TypeDecoder.decode[UInt168]("0000000000000000000000ffffffffffffffffffffffffffffffffffffffffff") == UInt168(BigInt("374144419156711147060143317175368453031918731001855")))
    assert(TypeDecoder.decode[UInt176]("0000000000000000000000000000000000000000000000000000000000000000") == UInt176(BigInt(0)))
    assert(TypeDecoder.decode[UInt176]("00000000000000000000ffffffffffffffffffffffffffffffffffffffffffff") == UInt176(BigInt("95780971304118053647396689196894323976171195136475135")))
    assert(TypeDecoder.decode[UInt184]("0000000000000000000000000000000000000000000000000000000000000000") == UInt184(BigInt(0)))
    assert(TypeDecoder.decode[UInt184]("000000000000000000ffffffffffffffffffffffffffffffffffffffffffffff") == UInt184(BigInt("24519928653854221733733552434404946937899825954937634815")))
    assert(TypeDecoder.decode[UInt192]("0000000000000000000000000000000000000000000000000000000000000000") == UInt192(BigInt(0)))
    assert(TypeDecoder.decode[UInt192]("0000000000000000ffffffffffffffffffffffffffffffffffffffffffffffff") == UInt192(BigInt("6277101735386680763835789423207666416102355444464034512895")))
    assert(TypeDecoder.decode[UInt200]("0000000000000000000000000000000000000000000000000000000000000000") == UInt200(BigInt(0)))
    assert(TypeDecoder.decode[UInt200]("00000000000000ffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt200(BigInt("1606938044258990275541962092341162602522202993782792835301375")))
    assert(TypeDecoder.decode[UInt208]("0000000000000000000000000000000000000000000000000000000000000000") == UInt208(BigInt(0)))
    assert(TypeDecoder.decode[UInt208]("000000000000ffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt208(BigInt("411376139330301510538742295639337626245683966408394965837152255")))
    assert(TypeDecoder.decode[UInt216]("0000000000000000000000000000000000000000000000000000000000000000") == UInt216(BigInt(0)))
    assert(TypeDecoder.decode[UInt216]("0000000000ffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt216(BigInt("105312291668557186697918027683670432318895095400549111254310977535")))
    assert(TypeDecoder.decode[UInt224]("0000000000000000000000000000000000000000000000000000000000000000") == UInt224(BigInt(0)))
    assert(TypeDecoder.decode[UInt224]("00000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt224(BigInt("26959946667150639794667015087019630673637144422540572481103610249215")))
    assert(TypeDecoder.decode[UInt232]("0000000000000000000000000000000000000000000000000000000000000000") == UInt232(BigInt(0)))
    assert(TypeDecoder.decode[UInt232]("000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt232(BigInt("6901746346790563787434755862277025452451108972170386555162524223799295")))
    assert(TypeDecoder.decode[UInt240]("0000000000000000000000000000000000000000000000000000000000000000") == UInt240(BigInt(0)))
    assert(TypeDecoder.decode[UInt240]("0000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt240(BigInt("1766847064778384329583297500742918515827483896875618958121606201292619775")))
    assert(TypeDecoder.decode[UInt248]("0000000000000000000000000000000000000000000000000000000000000000") == UInt248(BigInt(0)))
    assert(TypeDecoder.decode[UInt248]("00ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt248(BigInt("452312848583266388373324160190187140051835877600158453279131187530910662655")))
    assert(TypeDecoder.decode[UInt256]("0000000000000000000000000000000000000000000000000000000000000000") == UInt256(BigInt(0)))
    assert(TypeDecoder.decode[UInt256]("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == UInt256(BigInt("115792089237316195423570985008687907853269984665640564039457584007913129639935")))
    //    assert(TypeDecoder.instantiateType("uint", 123), new Nothing(BigInt(123)))
    //    assert(TypeDecoder.instantiateType("uint", 1.0e20), new Nothing(BigInt.TEN.pow(20)))
    //    assert(TypeDecoder.instantiateType("uint", 123), new Nothing(BigInt(123)))
    //    assert(TypeDecoder.instantiateType("uint", 1.0e20), new Nothing(BigInt.TEN.pow(20)))

  }

  test("IntDecode") {
    assert(TypeDecoder.decode[Int8]("0000000000000000000000000000000000000000000000000000000000000000") == Int8(BigInt(0)))
    assert(TypeDecoder.decode[Int8]("000000000000000000000000000000000000000000000000000000000000007f") == Int8(BigInt(127)))
    assert(TypeDecoder.decode[Int16]("0000000000000000000000000000000000000000000000000000000000000000") == Int16(BigInt(0)))
    assert(TypeDecoder.decode[Int16]("0000000000000000000000000000000000000000000000000000000000007FFF") == Int16(BigInt(32767)))
    assert(TypeDecoder.decode[Int24]("0000000000000000000000000000000000000000000000000000000000000000") == Int24(BigInt(0)))
    assert(TypeDecoder.decode[Int24]("00000000000000000000000000000000000000000000000000000000007fffff") == Int24(BigInt(8388607)))
    assert(TypeDecoder.decode[Int32]("0000000000000000000000000000000000000000000000000000000000000000") == Int32(BigInt(0)))
    assert(TypeDecoder.decode[Int32]("000000000000000000000000000000000000000000000000000000007fffffff") == Int32(BigInt(2147483647)))
    assert(TypeDecoder.decode[Int40]("0000000000000000000000000000000000000000000000000000000000000000") == Int40(BigInt(0)))
    assert(TypeDecoder.decode[Int40]("0000000000000000000000000000000000000000000000000000007FFFFFFFFF") == Int40(BigInt(549755813887L)))
    assert(TypeDecoder.decode[Int48]("0000000000000000000000000000000000000000000000000000000000000000") == Int48(BigInt(0)))
    assert(TypeDecoder.decode[Int48]("00000000000000000000000000000000000000000000000000007FFFFFFFFFFF") == Int48(BigInt(140737488355327L)))
    assert(TypeDecoder.decode[Int56]("0000000000000000000000000000000000000000000000000000000000000000") == Int56(BigInt(0)))
    assert(TypeDecoder.decode[Int56]("000000000000000000000000000000000000000000000000007FFFFFFFFFFFFF") == Int56(BigInt(36028797018963967L)))
    assert(TypeDecoder.decode[Int64]("0000000000000000000000000000000000000000000000000000000000000000") == Int64(BigInt(0)))
    assert(TypeDecoder.decode[Int64]("0000000000000000000000000000000000000000000000007fffffffffffffff") == Int64(BigInt(Long.MaxValue)))
    assert(TypeDecoder.decode[Int64]("fffffffffffffffffffffffffffffffffffffffffffffff88000000000000000") == Int64(BigInt(Long.MinValue)))
    assert(TypeDecoder.decode[Int64]("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int64(BigInt(-1)))
    assert(TypeDecoder.decode[Int72]("0000000000000000000000000000000000000000000000000000000000000000") == Int72(BigInt(0)))
    assert(TypeDecoder.decode[Int72]("00000000000000000000000000000000000000000000007fffffffffffffffff") == Int72(BigInt("2361183241434822606847")))
    assert(TypeDecoder.decode[Int80]("0000000000000000000000000000000000000000000000000000000000000000") == Int80(BigInt(0)))
    assert(TypeDecoder.decode[Int80]("000000000000000000000000000000000000000000007fffffffffffffffffff") == Int80(BigInt("604462909807314587353087")))
    assert(TypeDecoder.decode[Int88]("0000000000000000000000000000000000000000000000000000000000000000") == Int88(BigInt(0)))
    assert(TypeDecoder.decode[Int88]("0000000000000000000000000000000000000000007fffffffffffffffffffff") == Int88(BigInt("154742504910672534362390527")))
    assert(TypeDecoder.decode[Int96]("0000000000000000000000000000000000000000000000000000000000000000") == Int96(BigInt(0)))
    assert(TypeDecoder.decode[Int96]("00000000000000000000000000000000000000007fffffffffffffffffffffff") == Int96(BigInt("39614081257132168796771975167")))
    assert(TypeDecoder.decode[Int104]("0000000000000000000000000000000000000000000000000000000000000000") == Int104(BigInt(0)))
    assert(TypeDecoder.decode[Int104]("000000000000000000000000000000000000007fffffffffffffffffffffffff") == Int104(BigInt("10141204801825835211973625643007")))
    assert(TypeDecoder.decode[Int112]("0000000000000000000000000000000000000000000000000000000000000000") == Int112(BigInt(0)))
    assert(TypeDecoder.decode[Int112]("0000000000000000000000000000000000007fffffffffffffffffffffffffff") == Int112(BigInt("2596148429267413814265248164610047")))
    assert(TypeDecoder.decode[Int120]("0000000000000000000000000000000000000000000000000000000000000000") == Int120(BigInt(0)))
    assert(TypeDecoder.decode[Int120]("00000000000000000000000000000000007fffffffffffffffffffffffffffff") == Int120(BigInt("664613997892457936451903530140172287")))
    assert(TypeDecoder.decode[Int128]("0000000000000000000000000000000000000000000000000000000000000000") == Int128(BigInt(0)))
    assert(TypeDecoder.decode[Int128]("000000000000000000000000000000007fffffffffffffffffffffffffffffff") == Int128(BigInt("170141183460469231731687303715884105727")))
    assert(TypeDecoder.decode[Int136]("0000000000000000000000000000000000000000000000000000000000000000") == Int136(BigInt(0)))
    assert(TypeDecoder.decode[Int136]("0000000000000000000000000000007fffffffffffffffffffffffffffffffff") == Int136(BigInt("43556142965880123323311949751266331066367")))
    assert(TypeDecoder.decode[Int144]("0000000000000000000000000000000000000000000000000000000000000000") == Int144(BigInt(0)))
    assert(TypeDecoder.decode[Int144]("00000000000000000000000000007fffffffffffffffffffffffffffffffffff") == Int144(BigInt("11150372599265311570767859136324180752990207")))
    assert(TypeDecoder.decode[Int152]("0000000000000000000000000000000000000000000000000000000000000000") == Int152(BigInt(0)))
    assert(TypeDecoder.decode[Int152]("000000000000000000000000007fffffffffffffffffffffffffffffffffffff") == Int152(BigInt("2854495385411919762116571938898990272765493247")))
    assert(TypeDecoder.decode[Int160]("0000000000000000000000000000000000000000000000000000000000000000") == Int160(BigInt(0)))
    assert(TypeDecoder.decode[Int160]("0000000000000000000000007fffffffffffffffffffffffffffffffffffffff") == Int160(BigInt("730750818665451459101842416358141509827966271487")))
    assert(TypeDecoder.decode[Int168]("0000000000000000000000000000000000000000000000000000000000000000") == Int168(BigInt(0)))
    assert(TypeDecoder.decode[Int168]("00000000000000000000007fffffffffffffffffffffffffffffffffffffffff") == Int168(BigInt("187072209578355573530071658587684226515959365500927")))
    assert(TypeDecoder.decode[Int176]("0000000000000000000000000000000000000000000000000000000000000000") == Int176(BigInt(0)))
    assert(TypeDecoder.decode[Int176]("000000000000000000007fffffffffffffffffffffffffffffffffffffffffff") == Int176(BigInt("47890485652059026823698344598447161988085597568237567")))
    assert(TypeDecoder.decode[Int184]("0000000000000000000000000000000000000000000000000000000000000000") == Int184(BigInt(0)))
    assert(TypeDecoder.decode[Int184]("0000000000000000007fffffffffffffffffffffffffffffffffffffffffffff") == Int184(BigInt("12259964326927110866866776217202473468949912977468817407")))
    assert(TypeDecoder.decode[Int192]("0000000000000000000000000000000000000000000000000000000000000000") == Int192(BigInt(0)))
    assert(TypeDecoder.decode[Int192]("00000000000000007fffffffffffffffffffffffffffffffffffffffffffffff") == Int192(BigInt("3138550867693340381917894711603833208051177722232017256447")))
    assert(TypeDecoder.decode[Int200]("0000000000000000000000000000000000000000000000000000000000000000") == Int200(BigInt(0)))
    assert(TypeDecoder.decode[Int200]("000000000000007fffffffffffffffffffffffffffffffffffffffffffffffff") == Int200(BigInt("803469022129495137770981046170581301261101496891396417650687")))
    assert(TypeDecoder.decode[Int208]("0000000000000000000000000000000000000000000000000000000000000000") == Int208(BigInt(0)))
    assert(TypeDecoder.decode[Int208]("0000000000007fffffffffffffffffffffffffffffffffffffffffffffffffff") == Int208(BigInt("205688069665150755269371147819668813122841983204197482918576127")))
    assert(TypeDecoder.decode[Int216]("0000000000000000000000000000000000000000000000000000000000000000") == Int216(BigInt(0)))
    assert(TypeDecoder.decode[Int216]("00000000007fffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int216(BigInt("52656145834278593348959013841835216159447547700274555627155488767")))
    assert(TypeDecoder.decode[Int224]("0000000000000000000000000000000000000000000000000000000000000000") == Int224(BigInt(0)))
    assert(TypeDecoder.decode[Int224]("000000007fffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int224(BigInt("13479973333575319897333507543509815336818572211270286240551805124607")))
    assert(TypeDecoder.decode[Int232]("0000000000000000000000000000000000000000000000000000000000000000") == Int232(BigInt(0)))
    assert(TypeDecoder.decode[Int232]("0000007fffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int232(BigInt("3450873173395281893717377931138512726225554486085193277581262111899647")))
    assert(TypeDecoder.decode[Int240]("0000000000000000000000000000000000000000000000000000000000000000") == Int240(BigInt(0)))
    assert(TypeDecoder.decode[Int240]("00007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int240(BigInt("883423532389192164791648750371459257913741948437809479060803100646309887")))
    assert(TypeDecoder.decode[Int248]("0000000000000000000000000000000000000000000000000000000000000000") == Int248(BigInt(0)))
    assert(TypeDecoder.decode[Int248]("007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int248(BigInt("226156424291633194186662080095093570025917938800079226639565593765455331327")))
    assert(TypeDecoder.decode[Int256]("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff") == Int256(BigInt(-(1))))
    //    assert(TypeDecoder.instantiateType("int", 123), new Nothing(BigInt(123)))
    //    assert(TypeDecoder.instantiateType("int", -123), new Nothing(BigInt(-(123))))
  }
  //
  //  /*
  //      TODO: Enable once Solidity supports fixed types - see
  //      https://github.com/ethereum/solidity/issues/409
  //
  //      @Test
  //      public void testUfixedDecode() {
  //          assert(TypeDecoder.decode[Int](
  //                  "0000000000000000000000000000000000000000000000000000000000000000",
  //                  Ufixed24x40.class),
  //                  (new Ufixed24x40(BigInt(0))));
  //
  //          assert(TypeDecoder.decode[Int](
  //                  "0000000000000000000000000000000000000000000000007fffffffffffffff",
  //                  Ufixed24x40.class),
  //                  (new Ufixed24x40(BigInt(Long.MAX_VALUE))));
  //
  //          assert(TypeDecoder.decode[Int](
  //                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
  //                  Ufixed.class),
  //                  (new Ufixed(
  //                          new BigInt(
  //                                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
  //                                  16
  //                          ))));
  //      }
  //
  //      @Test
  //      public void testFixedDecode() {
  //          assert(TypeDecoder.decode[Int](
  //                  "0000000000000000000000000000000000000000000000000000000000000000",
  //                  Fixed24x40.class),
  //                  (new Fixed24x40(BigInt(0))));
  //
  //          assert(TypeDecoder.decode[Int](
  //                  "0000000000000000000000000000000000000000000000007fffffffffffffff",
  //                  Fixed24x40.class),
  //                  (new Fixed24x40(BigInt(Long.MAX_VALUE))));
  //
  //          assert(TypeDecoder.decode[Int](
  //                  "ffffffffffffffffffffffffffffffffffffffffffffffff8000000000000000",
  //                  Fixed24x40.class),
  //                  (new Fixed24x40(BigInt(Long.MIN_VALUE))));
  //
  //          assert(TypeDecoder.decode[Int](
  //                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
  //                  Fixed.class),
  //                  (new Fixed(BigInt(-1))));
  //      }
  //      */
  test("StaticBytes") {
    val testbytes = Array[Byte](0, 1, 2, 3, 4, 5)
    val staticBytes = Bytes6(testbytes)
    assert(TypeDecoder.decode[Bytes6]("0001020304050000000000000000000000000000000000000000000000000000") == staticBytes)
    val empty = Bytes1(Array[Byte](0))
    assert(TypeDecoder.decode[Bytes1]("0000000000000000000000000000000000000000000000000000000000000000") == empty)
    val dave = Bytes4("dave".getBytes)
    assert(TypeDecoder.decode[Bytes4]("6461766500000000000000000000000000000000000000000000000000000000") == dave)
    //assert(TypeDecoder.instantiateType("bytes6", testbytes), new Nothing(testbytes))
  }

  test("DynamicBytes") {
    val testbytes = Array[Byte](0, 1, 2, 3, 4, 5)
    val dynamicBytes = DynamicBytes(testbytes)
    assert(TypeDecoder.decode[DynamicBytes]("0000000000000000000000000000000000000000000000000000000000000006"  + "0001020304050000000000000000000000000000000000000000000000000000", 0) == dynamicBytes)
    val empty = DynamicBytes(Array[Byte](0))
    assert(TypeDecoder.decode[DynamicBytes]("0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000", 0) == empty)
    val dave = DynamicBytes("dave".getBytes)
    assert(TypeDecoder.decode[DynamicBytes]("0000000000000000000000000000000000000000000000000000000000000004" + "6461766500000000000000000000000000000000000000000000000000000000", 0) == dave)
    val loremIpsum = DynamicBytes(("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " + "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " + "ea commodo consequat. Duis aute irure dolor in reprehenderit in " + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " + "sint occaecat cupidatat non proident, sunt in culpa qui officia " + "deserunt mollit anim id est laborum.").getBytes)
    assert(TypeDecoder.decode[DynamicBytes]("00000000000000000000000000000000000000000000000000000000000001bd" + "4c6f72656d20697073756d20646f6c6f722073697420616d65742c20636f6e73" + "656374657475722061646970697363696e6720656c69742c2073656420646f20" + "656975736d6f642074656d706f7220696e6369646964756e74207574206c6162" + "6f726520657420646f6c6f7265206d61676e6120616c697175612e2055742065" + "6e696d206164206d696e696d2076656e69616d2c2071756973206e6f73747275" + "6420657865726369746174696f6e20756c6c616d636f206c61626f726973206e" + "69736920757420616c697175697020657820656120636f6d6d6f646f20636f6e" + "7365717561742e2044756973206175746520697275726520646f6c6f7220696e" + "20726570726568656e646572697420696e20766f6c7570746174652076656c69" + "7420657373652063696c6c756d20646f6c6f726520657520667567696174206e" + "756c6c612070617269617475722e204578636570746575722073696e74206f63" + "63616563617420637570696461746174206e6f6e2070726f6964656e742c2073" + "756e7420696e2063756c706120717569206f666669636961206465736572756e" + "74206d6f6c6c697420616e696d20696420657374206c61626f72756d2e000000", 0) == loremIpsum)
    //assert(TypeDecoder.instantiateType("bytes", testbytes), new Nothing(testbytes))
  }
//
  test("Address") {
    assert(TypeDecoder.decode[Address]("000000000000000000000000be5422d15f39373eb0a97ff8c10fbd0e40e29338") == Address("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
    assert(TypeDecoder.decode[Address]("000000000000000000000000be5422d15f39373eb0a97ff8c10fbd0e40e29338") == Address("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
   // assert(TypeDecoder.instantiateType("address", "0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"), new Nothing("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
   // assert(TypeDecoder.instantiateType("address", BigInt.ONE), new Nothing("0x0000000000000000000000000000000000000001"))
  }
//
  test("Utf8String") {
    assert(TypeDecoder.decode[EthUtf8String]("000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000", 0) == EthUtf8String("Hello, world!"))
 //   assert(TypeDecoder.instantiateType("string", "Hello, world!"), new Nothing("Hello, world!"))
  }

  test("StaticArray"){
   // println(DecoderMacro.showSeqType[StaticArray1])
//    //print(DecoderMacro.showType[UInt256])
    //println(decodeStaticArray[StaticArray2])
    assert(TypeDecoder.decode[StaticArray2,UInt256]("000000000000000000000000000000000000000000000000000000000000000a" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 0,  2) == Seq(UInt256(BigInt(10)), UInt256(BigInt(Long.MaxValue))))
//   // assert(TypeDecoder.decode[StaticArray("0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" + "000000000000000000000000000000000000000000000000000000000000000d" + "776f726c64212048656c6c6f2c00000000000000000000000000000000000000", 0, new Nothing(2) {}, 2), new Nothing(classOf[Nothing], new Nothing("Hello, world!"), new Nothing("world! Hello,")))
////    val arr = TypeDecoder.instantiateType("uint256[2]", Array[Long](10, Long.MAX_VALUE))
////    assertTrue(arr.isInstanceOf[Nothing])
////    val staticArray2 = arr.asInstanceOf[Nothing]
////    assert(staticArray2.getComponentType, classOf[Nothing])
////    assert(staticArray2.getValue.get(0), new Nothing(BigInt.TEN))
////    assert(staticArray2.getValue.get(1), new Nothing(BigInt(Long.MAX_VALUE)))
  }
//
//  @Test def testEmptyStaticArray() = assertThrows(classOf[UnsupportedOperationException], () => TypeDecoder.decodeStaticArray("0000000000000000000000000000000000000000000000000000000000000000", 0, new Nothing(0) {}, 0))
//
//  @Test
//  @throws[Exception]
//  def testDynamicArray() = {
//    assert(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000000", // length
//      0, new Nothing() {}), new Nothing(classOf[Nothing]))
//    assert(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000002" + "000000000000000000000000000000000000000000000000000000000000000a" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 0, new Nothing() {}), new Nothing(classOf[Nothing], new Nothing(BigInt.TEN), new Nothing(BigInt(Long.MAX_VALUE))))
//    assert(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000002" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" + "000000000000000000000000000000000000000000000000000000000000000d" + "776f726c64212048656c6c6f2c00000000000000000000000000000000000000", 0, new Nothing() {}), new Nothing(classOf[Nothing], new Nothing("Hello, world!"), new Nothing("world! Hello,")))
//    val arr = TypeDecoder.instantiateType("string[]", Array[String]("Hello, world!", "world! Hello,"))
//    assertTrue(arr.isInstanceOf[Nothing])
//    val dynamicArray = arr.asInstanceOf[Nothing]
//    assert(dynamicArray.getComponentType, classOf[Nothing])
//    assert(dynamicArray.getValue.get(0), new Nothing("Hello, world!"))
//    assert(dynamicArray.getValue.get(1), new Nothing("world! Hello,"))
//  }
//
//  def multiDimArrays() = {
//    val bytes1d = Array[Byte](1, 2, 3)
//    val bytes2d = Array[Array[Byte]](bytes1d, bytes1d, bytes1d)
//    val bytes3d = Array[Array[Array[Byte]]](bytes2d, bytes2d, bytes2d)
//    assert(TypeDecoder.instantiateType("bytes", bytes1d), new Nothing(bytes1d))
//    val twoDim = TypeDecoder.instantiateType("uint256[][3]", bytes2d)
//    assertTrue(twoDim.isInstanceOf[Nothing])
//    val staticArray3 = twoDim.asInstanceOf[Nothing]
//    assert(staticArray3.getComponentType, classOf[Nothing])
//    var row1 = staticArray3.getValue.get(1)
//    assert(row1.getValue.get(2), new Nothing(3))
//    val threeDim = TypeDecoder.instantiateType("uint256[][3][3]", bytes3d)
//    assertTrue(threeDim.isInstanceOf[Nothing])
//    val staticArray3StaticArray3 = threeDim.asInstanceOf[Nothing]
//    assert(staticArray3StaticArray3.getComponentType, classOf[Nothing])
//    row1 = staticArray3StaticArray3.getValue.get(1).getValue.get(1)
//    assert(row1.getValue.get(1), new Nothing(2))
//  }
