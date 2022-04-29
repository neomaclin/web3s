package org.web3s.abit

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.{Bool, NumericType, SolidityInt}

class TypeDecoderTest extends AnyFunSuite :

  test("BoolDecode")  {
    assert(Bool.decode("0000000000000000000000000000000000000000000000000000000000000000", 0) == new Bool(false))
    assert(Bool.decode("0000000000000000000000000000000000000000000000000000000000000001", 0) == new Bool(true))
//    assert(TypeDecoder.instantiateType("bool", true), new Nothing(true))
//    assert(TypeDecoder.instantiateType("bool", 1), new Nothing(true))
//    assert(TypeDecoder.instantiateType("bool", false), new Nothing(false))
//    assert(TypeDecoder.instantiateType("bool", 0), new Nothing(false))
  }
//
  test("BoolDecodeGivenOffset")  { // Decode second parameter as Bool
    assert(Bool.decode("0000000000000000000000000000000000000000000000007fffffffffffffff" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 64) == new Bool(false))
    assert(Bool.decode("0000000000000000000000000000000000000000000000007fffffffffffffff" + "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 64) == new Bool(true))
  }
  
//  test("UintDecode"){
//    val test = NumericType.decode[SolidityInt]("0000000000000000000000000000000000000000000000000000000000000000")
//  }
//
//  def testUintDecode() = {
//    assertEquals(TypeDecoder.instantiateType("uint", 123), new Nothing(BigInteger.valueOf(123)))
//    assertEquals(TypeDecoder.instantiateType("uint", 1.0e20), new Nothing(BigInteger.TEN.pow(20)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000000000000000000000ff", classOf[Nothing]), new Nothing(BigInteger.valueOf(255)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000000000000000000000ffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(65535)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000ffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(16777215)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000000000000000ffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(4294967295L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000000000000000ffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(1099511627775L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000ffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(281474976710655L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000000000ffffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(72057594037927935L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000007fffffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(Long.MAX_VALUE)))
//    assertEquals(TypeDecoder.decodeNumeric("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("0ffffffffffffffff", 16)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000ffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("4722366482869645213695")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000ffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("1208925819614629174706175")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000ffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("309485009821345068724781055")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000ffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("79228162514264337593543950335")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000ffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("20282409603651670423947251286015")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000ffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("5192296858534827628530496329220095")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000ffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("1329227995784915872903807060280344575")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000ffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("340282366920938463463374607431768211455")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000ffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("87112285931760246646623899502532662132735")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000ffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("22300745198530623141535718272648361505980415")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000ffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("5708990770823839524233143877797980545530986495")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000ffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("1461501637330902918203684832716283019655932542975")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000ffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("374144419156711147060143317175368453031918731001855")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000ffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("95780971304118053647396689196894323976171195136475135")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000ffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("24519928653854221733733552434404946937899825954937634815")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000ffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("6277101735386680763835789423207666416102355444464034512895")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000ffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("1606938044258990275541962092341162602522202993782792835301375")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000ffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("411376139330301510538742295639337626245683966408394965837152255")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000ffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("105312291668557186697918027683670432318895095400549111254310977535")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("26959946667150639794667015087019630673637144422540572481103610249215")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("6901746346790563787434755862277025452451108972170386555162524223799295")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("1766847064778384329583297500742918515827483896875618958121606201292619775")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("452312848583266388373324160190187140051835877600158453279131187530910662655")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")))
//    assertEquals(TypeDecoder.instantiateType("uint", 123), new Nothing(BigInteger.valueOf(123)))
//    assertEquals(TypeDecoder.instantiateType("uint", 1.0e20), new Nothing(BigInteger.TEN.pow(20)))
//  }
//
//  def testIntDecode() = {
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000000000000000000000007f", classOf[Nothing]), new Nothing(BigInteger.valueOf(127)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000007FFF", classOf[Nothing]), new Nothing(BigInteger.valueOf(32767)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000000000000000007fffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(8388607)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000000000000000007fffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(2147483647)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000007FFFFFFFFF", classOf[Nothing]), new Nothing(BigInteger.valueOf(549755813887L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000000000007FFFFFFFFFFF", classOf[Nothing]), new Nothing(BigInteger.valueOf(140737488355327L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000000000007FFFFFFFFFFFFF", classOf[Nothing]), new Nothing(BigInteger.valueOf(36028797018963967L)))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000007fffffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(Long.MAX_VALUE)))
//    assertEquals(TypeDecoder.decodeNumeric("fffffffffffffffffffffffffffffffffffffffffffffff88000000000000000", classOf[Nothing]), new Nothing(BigInteger.valueOf(Long.MIN_VALUE)))
//    assertEquals(TypeDecoder.decodeNumeric("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(-(1))))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000000000007fffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("2361183241434822606847")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000000000007fffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("604462909807314587353087")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000007fffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("154742504910672534362390527")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000000000007fffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("39614081257132168796771975167")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000000000007fffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("10141204801825835211973625643007")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000007fffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("2596148429267413814265248164610047")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000000000007fffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("664613997892457936451903530140172287")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000000000007fffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("170141183460469231731687303715884105727")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000007fffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("43556142965880123323311949751266331066367")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000000000007fffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("11150372599265311570767859136324180752990207")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000000000007fffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("2854495385411919762116571938898990272765493247")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000007fffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("730750818665451459101842416358141509827966271487")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000000000007fffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("187072209578355573530071658587684226515959365500927")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000000000007fffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("47890485652059026823698344598447161988085597568237567")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000007fffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("12259964326927110866866776217202473468949912977468817407")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000000000007fffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("3138550867693340381917894711603833208051177722232017256447")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000000000007fffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("803469022129495137770981046170581301261101496891396417650687")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000007fffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("205688069665150755269371147819668813122841983204197482918576127")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00000000007fffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("52656145834278593348959013841835216159447547700274555627155488767")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("000000007fffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("13479973333575319897333507543509815336818572211270286240551805124607")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("0000007fffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("3450873173395281893717377931138512726225554486085193277581262111899647")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("00007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("883423532389192164791648750371459257913741948437809479060803100646309887")))
//    assertEquals(TypeDecoder.decodeNumeric("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), new Nothing(BigInteger.ZERO))
//    assertEquals(TypeDecoder.decodeNumeric("007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(new BigInteger("226156424291633194186662080095093570025917938800079226639565593765455331327")))
//    assertEquals(TypeDecoder.decodeNumeric("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", classOf[Nothing]), new Nothing(BigInteger.valueOf(-(1))))
//    assertEquals(TypeDecoder.instantiateType("int", 123), new Nothing(BigInteger.valueOf(123)))
//    assertEquals(TypeDecoder.instantiateType("int", -123), new Nothing(BigInteger.valueOf(-(123))))
//  }
//
//  /*
//      TODO: Enable once Solidity supports fixed types - see
//      https://github.com/ethereum/solidity/issues/409
//
//      @Test
//      public void testUfixedDecode() {
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "0000000000000000000000000000000000000000000000000000000000000000",
//                  Ufixed24x40.class),
//                  (new Ufixed24x40(BigInteger.ZERO)));
//
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "0000000000000000000000000000000000000000000000007fffffffffffffff",
//                  Ufixed24x40.class),
//                  (new Ufixed24x40(BigInteger.valueOf(Long.MAX_VALUE))));
//
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
//                  Ufixed.class),
//                  (new Ufixed(
//                          new BigInteger(
//                                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
//                                  16
//                          ))));
//      }
//
//      @Test
//      public void testFixedDecode() {
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "0000000000000000000000000000000000000000000000000000000000000000",
//                  Fixed24x40.class),
//                  (new Fixed24x40(BigInteger.ZERO)));
//
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "0000000000000000000000000000000000000000000000007fffffffffffffff",
//                  Fixed24x40.class),
//                  (new Fixed24x40(BigInteger.valueOf(Long.MAX_VALUE))));
//
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "ffffffffffffffffffffffffffffffffffffffffffffffff8000000000000000",
//                  Fixed24x40.class),
//                  (new Fixed24x40(BigInteger.valueOf(Long.MIN_VALUE))));
//
//          assertEquals(TypeDecoder.decodeNumeric(
//                  "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
//                  Fixed.class),
//                  (new Fixed(BigInteger.valueOf(-1))));
//      }
//      */
//  def testStaticBytes() = {
//    val testbytes = Array[Byte](0, 1, 2, 3, 4, 5)
//    val staticBytes = new Nothing(testbytes)
//    assertEquals(TypeDecoder.decodeBytes("0001020304050000000000000000000000000000000000000000000000000000", classOf[Nothing]), staticBytes)
//    val empty = new Nothing(Array[Byte](0))
//    assertEquals(TypeDecoder.decodeBytes("0000000000000000000000000000000000000000000000000000000000000000", classOf[Nothing]), empty)
//    val dave = new Nothing("dave".getBytes)
//    assertEquals(TypeDecoder.decodeBytes("6461766500000000000000000000000000000000000000000000000000000000", classOf[Nothing]), dave)
//    assertEquals(TypeDecoder.instantiateType("bytes6", testbytes), new Nothing(testbytes))
//  }
//
//  def testDynamicBytes() = {
//    val testbytes = Array[Byte](0, 1, 2, 3, 4, 5)
//    val dynamicBytes = new Nothing(testbytes)
//    assertEquals(TypeDecoder.decodeDynamicBytes("0000000000000000000000000000000000000000000000000000000000000006" // length + "0001020304050000000000000000000000000000000000000000000000000000", 0), (dynamicBytes))
//    val empty = new Nothing(Array[Byte](0))
//    assertEquals(TypeDecoder.decodeDynamicBytes("0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000", 0), empty)
//    val dave = new Nothing("dave".getBytes)
//    assertEquals(TypeDecoder.decodeDynamicBytes("0000000000000000000000000000000000000000000000000000000000000004" + "6461766500000000000000000000000000000000000000000000000000000000", 0), dave)
//    val loremIpsum = new Nothing(("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " + "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " + "ea commodo consequat. Duis aute irure dolor in reprehenderit in " + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " + "sint occaecat cupidatat non proident, sunt in culpa qui officia " + "deserunt mollit anim id est laborum.").getBytes)
//    assertEquals(TypeDecoder.decodeDynamicBytes("00000000000000000000000000000000000000000000000000000000000001bd" + "4c6f72656d20697073756d20646f6c6f722073697420616d65742c20636f6e73" + "656374657475722061646970697363696e6720656c69742c2073656420646f20" + "656975736d6f642074656d706f7220696e6369646964756e74207574206c6162" + "6f726520657420646f6c6f7265206d61676e6120616c697175612e2055742065" + "6e696d206164206d696e696d2076656e69616d2c2071756973206e6f73747275" + "6420657865726369746174696f6e20756c6c616d636f206c61626f726973206e" + "69736920757420616c697175697020657820656120636f6d6d6f646f20636f6e" + "7365717561742e2044756973206175746520697275726520646f6c6f7220696e" + "20726570726568656e646572697420696e20766f6c7570746174652076656c69" + "7420657373652063696c6c756d20646f6c6f726520657520667567696174206e" + "756c6c612070617269617475722e204578636570746575722073696e74206f63" + "63616563617420637570696461746174206e6f6e2070726f6964656e742c2073" + "756e7420696e2063756c706120717569206f666669636961206465736572756e" + "74206d6f6c6c697420616e696d20696420657374206c61626f72756d2e000000", 0), loremIpsum)
//    assertEquals(TypeDecoder.instantiateType("bytes", testbytes), new Nothing(testbytes))
//  }
//
//  def testAddress() = {
//    assertEquals(TypeDecoder.decodeAddress("000000000000000000000000be5422d15f39373eb0a97ff8c10fbd0e40e29338"), new Nothing("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
//    assertEquals(TypeDecoder.decodeAddress("000000000000000000000000be5422d15f39373eb0a97ff8c10fbd0e40e29338"), new Nothing("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
//    assertEquals(TypeDecoder.instantiateType("address", "0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"), new Nothing("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338"))
//    assertEquals(TypeDecoder.instantiateType("address", BigInteger.ONE), new Nothing("0x0000000000000000000000000000000000000001"))
//  }
//
//  def testUtf8String() = {
//    assertEquals(TypeDecoder.decodeUtf8String("000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000", 0), new Nothing("Hello, world!"))
//    assertEquals(TypeDecoder.instantiateType("string", "Hello, world!"), new Nothing("Hello, world!"))
//  }
//
//  def testStaticArray() = {
//    assertEquals(TypeDecoder.decodeStaticArray("000000000000000000000000000000000000000000000000000000000000000a" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 0, new Nothing(2) {}, 2), new Nothing(classOf[Nothing], new Nothing(BigInteger.TEN), new Nothing(BigInteger.valueOf(Long.MAX_VALUE))))
//    assertEquals(TypeDecoder.decodeStaticArray("0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" + "000000000000000000000000000000000000000000000000000000000000000d" + "776f726c64212048656c6c6f2c00000000000000000000000000000000000000", 0, new Nothing(2) {}, 2), new Nothing(classOf[Nothing], new Nothing("Hello, world!"), new Nothing("world! Hello,")))
//    val arr = TypeDecoder.instantiateType("uint256[2]", Array[Long](10, Long.MAX_VALUE))
//    assertTrue(arr.isInstanceOf[Nothing])
//    val staticArray2 = arr.asInstanceOf[Nothing]
//    assertEquals(staticArray2.getComponentType, classOf[Nothing])
//    assertEquals(staticArray2.getValue.get(0), new Nothing(BigInteger.TEN))
//    assertEquals(staticArray2.getValue.get(1), new Nothing(BigInteger.valueOf(Long.MAX_VALUE)))
//  }
//
//  @Test def testEmptyStaticArray() = assertThrows(classOf[UnsupportedOperationException], () => TypeDecoder.decodeStaticArray("0000000000000000000000000000000000000000000000000000000000000000", 0, new Nothing(0) {}, 0))
//
//  @Test
//  @throws[Exception]
//  def testDynamicArray() = {
//    assertEquals(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000000", // length
//      0, new Nothing() {}), new Nothing(classOf[Nothing]))
//    assertEquals(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000002" + "000000000000000000000000000000000000000000000000000000000000000a" + "0000000000000000000000000000000000000000000000007fffffffffffffff", 0, new Nothing() {}), new Nothing(classOf[Nothing], new Nothing(BigInteger.TEN), new Nothing(BigInteger.valueOf(Long.MAX_VALUE))))
//    assertEquals(TypeDecoder.decodeDynamicArray("0000000000000000000000000000000000000000000000000000000000000002" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" + "000000000000000000000000000000000000000000000000000000000000000d" + "776f726c64212048656c6c6f2c00000000000000000000000000000000000000", 0, new Nothing() {}), new Nothing(classOf[Nothing], new Nothing("Hello, world!"), new Nothing("world! Hello,")))
//    val arr = TypeDecoder.instantiateType("string[]", Array[String]("Hello, world!", "world! Hello,"))
//    assertTrue(arr.isInstanceOf[Nothing])
//    val dynamicArray = arr.asInstanceOf[Nothing]
//    assertEquals(dynamicArray.getComponentType, classOf[Nothing])
//    assertEquals(dynamicArray.getValue.get(0), new Nothing("Hello, world!"))
//    assertEquals(dynamicArray.getValue.get(1), new Nothing("world! Hello,"))
//  }
//
//  def multiDimArrays() = {
//    val bytes1d = Array[Byte](1, 2, 3)
//    val bytes2d = Array[Array[Byte]](bytes1d, bytes1d, bytes1d)
//    val bytes3d = Array[Array[Array[Byte]]](bytes2d, bytes2d, bytes2d)
//    assertEquals(TypeDecoder.instantiateType("bytes", bytes1d), new Nothing(bytes1d))
//    val twoDim = TypeDecoder.instantiateType("uint256[][3]", bytes2d)
//    assertTrue(twoDim.isInstanceOf[Nothing])
//    val staticArray3 = twoDim.asInstanceOf[Nothing]
//    assertEquals(staticArray3.getComponentType, classOf[Nothing])
//    var row1 = staticArray3.getValue.get(1)
//    assertEquals(row1.getValue.get(2), new Nothing(3))
//    val threeDim = TypeDecoder.instantiateType("uint256[][3][3]", bytes3d)
//    assertTrue(threeDim.isInstanceOf[Nothing])
//    val staticArray3StaticArray3 = threeDim.asInstanceOf[Nothing]
//    assertEquals(staticArray3StaticArray3.getComponentType, classOf[Nothing])
//    row1 = staticArray3StaticArray3.getValue.get(1).getValue.get(1)
//    assertEquals(row1.getValue.get(1), new Nothing(2))
//  }
end TypeDecoderTest