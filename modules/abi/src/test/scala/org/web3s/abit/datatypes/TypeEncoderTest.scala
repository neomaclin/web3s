package org.web3s.abit.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.datatypes.primitive.*

class TypeEncoderTest extends AnyFunSuite :
  test("BoolEncode") {
    assert(Bool.encode(Bool(false)) == "0000000000000000000000000000000000000000000000000000000000000000")
    assert(Bool.encode(Bool(true)) == "0000000000000000000000000000000000000000000000000000000000000001")
  }

  test("UIntEncode") {
    val zero8 = new UInt8(BigInt(0))
    assert(NumericType.encode(zero8) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max8 = new UInt8(255)
    assert(NumericType.encode(max8) == "00000000000000000000000000000000000000000000000000000000000000ff")
    val zero16 = new UInt16(BigInt(0))
    assert(NumericType.encode(zero16) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max16 = new UInt16(65535)
    assert(NumericType.encode(max16) == "000000000000000000000000000000000000000000000000000000000000ffff")
    val zero24 = new UInt24(BigInt(0))
    assert(NumericType.encode(zero24) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max24 = new UInt24(16777215)
    assert(NumericType.encode(max24) == "0000000000000000000000000000000000000000000000000000000000ffffff")
    val zero32 = new UInt32(BigInt(0))
    assert(NumericType.encode(zero32) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max32 = new UInt32(BigInt(4294967295L))
    assert(NumericType.encode(max32) == "00000000000000000000000000000000000000000000000000000000ffffffff")
    val zero40 = new UInt40(BigInt(0))
    assert(NumericType.encode(zero40) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max40 = new UInt40(BigInt(1099511627775L))
    assert(NumericType.encode(max40) == "000000000000000000000000000000000000000000000000000000ffffffffff")
    val zero48 = new UInt48(BigInt(0))
    assert(NumericType.encode(zero48) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max48 = new UInt48(BigInt(281474976710655L))
    assert(NumericType.encode(max48) == "0000000000000000000000000000000000000000000000000000ffffffffffff")
    val zero56 = new UInt56(BigInt(0))
    assert(NumericType.encode(zero56) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max56 = new UInt56(BigInt(72057594037927935L))
    assert(NumericType.encode(max56) == "00000000000000000000000000000000000000000000000000ffffffffffffff")
    val zero64 = new UInt64(BigInt(0))
    assert(NumericType.encode(zero64) == "0000000000000000000000000000000000000000000000000000000000000000")
    val maxLong = new UInt64(BigInt(Long.MaxValue))
    assert(NumericType.encode(maxLong) == "0000000000000000000000000000000000000000000000007fffffffffffffff")
    val maxValue64 = new SolidityUInt(BigInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16))
    assert(NumericType.encode(maxValue64) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val largeValue = new SolidityUInt(BigInt("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe", 16))
    assert(NumericType.encode(largeValue) == "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe")
    val zero72 = new UInt72(BigInt(0))
    assert(NumericType.encode(zero72) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max72 = new UInt72(BigInt("4722366482869645213695"))
    assert(NumericType.encode(max72) == "0000000000000000000000000000000000000000000000ffffffffffffffffff")
    val zero80 = new UInt80(BigInt(0))
    assert(NumericType.encode(zero80) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max80 = new UInt80(BigInt("1208925819614629174706175"))
    assert(NumericType.encode(max80) == "00000000000000000000000000000000000000000000ffffffffffffffffffff")
    val zero88 = new UInt88(BigInt(0))
    assert(NumericType.encode(zero88) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max88 = new UInt88(BigInt("309485009821345068724781055"))
    assert(NumericType.encode(max88) == "000000000000000000000000000000000000000000ffffffffffffffffffffff")
    val zero96 = new UInt96(BigInt(0))
    assert(NumericType.encode(zero96) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max96 = new UInt96(BigInt("79228162514264337593543950335"))
    assert(NumericType.encode(max96) == "0000000000000000000000000000000000000000ffffffffffffffffffffffff")
    val zero104 = new UInt104(BigInt(0))
    assert(NumericType.encode(zero104) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max104 = new UInt104(BigInt("20282409603651670423947251286015"))
    assert(NumericType.encode(max104) == "00000000000000000000000000000000000000ffffffffffffffffffffffffff")
    val zero112 = new UInt112(BigInt(0))
    assert(NumericType.encode(zero112) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max112 = new UInt112(BigInt("5192296858534827628530496329220095"))
    assert(NumericType.encode(max112) == "000000000000000000000000000000000000ffffffffffffffffffffffffffff")
    val zero120 = new UInt120(BigInt(0))
    assert(NumericType.encode(zero120) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max120 = new UInt120(BigInt("1329227995784915872903807060280344575"))
    assert(NumericType.encode(max120) == "0000000000000000000000000000000000ffffffffffffffffffffffffffffff")
    val zero128 = new UInt128(BigInt(0))
    assert(NumericType.encode(zero128) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max128 = new UInt128(BigInt("340282366920938463463374607431768211455"))
    assert(NumericType.encode(max128) == "00000000000000000000000000000000ffffffffffffffffffffffffffffffff")
    val zero136 = new UInt136(BigInt(0))
    assert(NumericType.encode(zero136) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max136 = new UInt136(BigInt("87112285931760246646623899502532662132735"))
    assert(NumericType.encode(max136) == "000000000000000000000000000000ffffffffffffffffffffffffffffffffff")
    val zero144 = new UInt144(BigInt(0))
    assert(NumericType.encode(zero144) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max144 = new UInt144(BigInt("22300745198530623141535718272648361505980415"))
    assert(NumericType.encode(max144) == "0000000000000000000000000000ffffffffffffffffffffffffffffffffffff")
    val zero152 = new UInt152(BigInt(0))
    assert(NumericType.encode(zero152) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max152 = new UInt152(BigInt("5708990770823839524233143877797980545530986495"))
    assert(NumericType.encode(max152) == "00000000000000000000000000ffffffffffffffffffffffffffffffffffffff")
    val zero160 = new UInt160(BigInt(0))
    assert(NumericType.encode(zero160) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max160 = new UInt160(BigInt("1461501637330902918203684832716283019655932542975"))
    assert(NumericType.encode(max160) == "000000000000000000000000ffffffffffffffffffffffffffffffffffffffff")
    val zero168 = new UInt168(BigInt(0))
    assert(NumericType.encode(zero168) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max168 = new UInt168(BigInt("374144419156711147060143317175368453031918731001855"))
    assert(NumericType.encode(max168) == "0000000000000000000000ffffffffffffffffffffffffffffffffffffffffff")
    val zero176 = new UInt176(BigInt(0))
    assert(NumericType.encode(zero176) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max176 = new UInt176(BigInt("95780971304118053647396689196894323976171195136475135"))
    assert(NumericType.encode(max176) == "00000000000000000000ffffffffffffffffffffffffffffffffffffffffffff")
    val zero184 = new UInt184(BigInt(0))
    assert(NumericType.encode(zero184) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max184 = new UInt184(BigInt("24519928653854221733733552434404946937899825954937634815"))
    assert(NumericType.encode(max184) == "000000000000000000ffffffffffffffffffffffffffffffffffffffffffffff")
    val zero192 = new UInt192(BigInt(0))
    assert(NumericType.encode(zero192) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max192 = new UInt192(BigInt("6277101735386680763835789423207666416102355444464034512895"))
    assert(NumericType.encode(max192) == "0000000000000000ffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero200 = new UInt200(BigInt(0))
    assert(NumericType.encode(zero200) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max200 = new UInt200(BigInt("1606938044258990275541962092341162602522202993782792835301375"))
    assert(NumericType.encode(max200) == "00000000000000ffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero208 = new UInt208(BigInt(0))
    assert(NumericType.encode(zero208) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max208 = new UInt208(BigInt("411376139330301510538742295639337626245683966408394965837152255"))
    assert(NumericType.encode(max208) == "000000000000ffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero216 = new UInt216(BigInt(0))
    assert(NumericType.encode(zero216) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max216 = new UInt216(BigInt("105312291668557186697918027683670432318895095400549111254310977535"))
    assert(NumericType.encode(max216) == "0000000000ffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero224 = new UInt224(BigInt(0))
    assert(NumericType.encode(zero224) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max224 = new UInt224(BigInt("26959946667150639794667015087019630673637144422540572481103610249215"))
    assert(NumericType.encode(max224) == "00000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero232 = new UInt232(BigInt(0))
    assert(NumericType.encode(zero232) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max232 = new UInt232(BigInt("6901746346790563787434755862277025452451108972170386555162524223799295"))
    assert(NumericType.encode(max232) == "000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero240 = new UInt240(BigInt(0))
    assert(NumericType.encode(zero240) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max240 = new UInt240(BigInt("1766847064778384329583297500742918515827483896875618958121606201292619775"))
    assert(NumericType.encode(max240) == "0000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val zero248 = new UInt248(BigInt(0))
    assert(NumericType.encode(zero248) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max248 = new UInt248(BigInt("452312848583266388373324160190187140051835877600158453279131187530910662655"))
    assert(NumericType.encode(max248) == "00ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
  }

  test("InvalidUintEncode") {
    assertThrows[IllegalArgumentException] {
      new SolidityUInt(BigInt(-1))
    }
  }

  test("TooLargeUintEncode") {
    // 1 more than "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
    assertThrows[IllegalArgumentException] {
      new SolidityUInt(BigInt("10000000000000000000000000000000000000000000000000000000000000000", 16))
    }
  }

  test("IntEncode") {
    val zero8 = new Int8(BigInt(0))
    assert(NumericType.encode(zero8) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max8 = new Int8(BigInt(127))
    assert(NumericType.encode(max8) == "000000000000000000000000000000000000000000000000000000000000007f")
    val min8 = new Int8(BigInt(-128))
    assert(NumericType.encode(min8) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80")
    val zero16 = new Int16(BigInt(0))
    assert(NumericType.encode(zero16) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max16 = new Int16(BigInt(32767))
    assert(NumericType.encode(max16) == "0000000000000000000000000000000000000000000000000000000000007fff")
    val min16 = new Int16(BigInt(-32768))
    assert(NumericType.encode(min16) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8000")
    val zero24 = new Int24(BigInt(0))
    assert(NumericType.encode(zero24) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max24 = new Int24(BigInt(8388607))
    assert(NumericType.encode(max24) == "00000000000000000000000000000000000000000000000000000000007fffff")
    val min24 = new Int24(BigInt(-8388608))
    assert(NumericType.encode(min24) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff800000")
    val zero32 = new Int32(BigInt(0))
    assert(NumericType.encode(zero32) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max32 = new Int32(BigInt(2147483647))
    assert(NumericType.encode(max32) == "000000000000000000000000000000000000000000000000000000007fffffff")
    val min32 = new Int32(BigInt(-2147483648))
    assert(NumericType.encode(min32) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffff80000000")
    val zero40 = new Int40(BigInt(0))
    assert(NumericType.encode(zero40) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max40 = new Int40(BigInt(549755813887L))
    assert(NumericType.encode(max40) == "0000000000000000000000000000000000000000000000000000007fffffffff")
    val min40 = new Int40(BigInt(-549755813888L))
    assert(NumericType.encode(min40) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffff8000000000")
    val zero48 = new Int48(BigInt(0))
    assert(NumericType.encode(zero48) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max48 = new Int48(BigInt(140737488355327L))
    assert(NumericType.encode(max48) == "00000000000000000000000000000000000000000000000000007fffffffffff")
    val min48 = new Int48(BigInt(-140737488355328L))
    assert(NumericType.encode(min48) == "ffffffffffffffffffffffffffffffffffffffffffffffffffff800000000000")
    val zero56 = new Int56(BigInt(0))
    assert(NumericType.encode(zero56) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max56 = new Int56(BigInt(36028797018963967L))
    assert(NumericType.encode(max56) == "000000000000000000000000000000000000000000000000007fffffffffffff")
    val min56 = new Int56(BigInt(-36028797018963968L))
    assert(NumericType.encode(min56) == "ffffffffffffffffffffffffffffffffffffffffffffffffff80000000000000")
    val zero64 = new Int64(BigInt(0))
    assert(NumericType.encode(zero64) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max64 = new Int64(BigInt(Long.MaxValue))
    assert(NumericType.encode(max64) == "0000000000000000000000000000000000000000000000007fffffffffffffff")
    val min64 = new Int64(BigInt(Long.MinValue))
    assert(NumericType.encode(min64) == "ffffffffffffffffffffffffffffffffffffffffffffffff8000000000000000")
    val zero72 = new Int72(BigInt(0))
    assert(NumericType.encode(zero72) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max72 = new Int72(BigInt("2361183241434822606847"))
    assert(NumericType.encode(max72) == "00000000000000000000000000000000000000000000007fffffffffffffffff")
    val min72 = new Int72(BigInt("-2361183241434822606848"))
    assert(NumericType.encode(min72) == "ffffffffffffffffffffffffffffffffffffffffffffff800000000000000000")
    val zero80 = new Int80(BigInt(0))
    assert(NumericType.encode(zero80) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max80 = new Int80(BigInt("604462909807314587353087"))
    assert(NumericType.encode(max80) == "000000000000000000000000000000000000000000007fffffffffffffffffff")
    val min80 = new Int80(BigInt("-604462909807314587353088"))
    assert(NumericType.encode(min80) == "ffffffffffffffffffffffffffffffffffffffffffff80000000000000000000")
    val zero88 = new Int88(BigInt(0))
    assert(NumericType.encode(zero88) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max88 = new Int88(BigInt("154742504910672534362390527"))
    assert(NumericType.encode(max88) == "0000000000000000000000000000000000000000007fffffffffffffffffffff")
    val min88 = new Int88(BigInt("-154742504910672534362390528"))
    assert(NumericType.encode(min88) == "ffffffffffffffffffffffffffffffffffffffffff8000000000000000000000")
    val zero96 = new Int96(BigInt(0))
    assert(NumericType.encode(zero96) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max96 = new Int96(BigInt("39614081257132168796771975167"))
    assert(NumericType.encode(max96) == "00000000000000000000000000000000000000007fffffffffffffffffffffff")
    val min96 = new Int96(BigInt("-39614081257132168796771975168"))
    assert(NumericType.encode(min96) == "ffffffffffffffffffffffffffffffffffffffff800000000000000000000000")
    val zero104 = new Int104(BigInt(0))
    assert(NumericType.encode(zero104) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max104 = new Int104(BigInt("10141204801825835211973625643007"))
    assert(NumericType.encode(max104) == "000000000000000000000000000000000000007fffffffffffffffffffffffff")
    val min104 = new Int104(BigInt("-10141204801825835211973625643008"))
    assert(NumericType.encode(min104) == "ffffffffffffffffffffffffffffffffffffff80000000000000000000000000")
    val zero112 = new Int112(BigInt(0))
    assert(NumericType.encode(zero112) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max112 = new Int112(BigInt("2596148429267413814265248164610047"))
    assert(NumericType.encode(max112) == "0000000000000000000000000000000000007fffffffffffffffffffffffffff")
    val min112 = new Int112(BigInt("-2596148429267413814265248164610048"))
    assert(NumericType.encode(min112) == "ffffffffffffffffffffffffffffffffffff8000000000000000000000000000")
    val zero120 = new Int120(BigInt(0))
    assert(NumericType.encode(zero120) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max120 = new Int120(BigInt("664613997892457936451903530140172287"))
    assert(NumericType.encode(max120) == "00000000000000000000000000000000007fffffffffffffffffffffffffffff")
    val min120 = new Int120(BigInt("-664613997892457936451903530140172288"))
    assert(NumericType.encode(min120) == "ffffffffffffffffffffffffffffffffff800000000000000000000000000000")
    val zero128 = new Int128(BigInt(0))
    assert(NumericType.encode(zero128) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max128 = new Int128(BigInt("170141183460469231731687303715884105727"))
    assert(NumericType.encode(max128) == "000000000000000000000000000000007fffffffffffffffffffffffffffffff")
    val min128 = new Int128(BigInt("-170141183460469231731687303715884105728"))
    assert(NumericType.encode(min128) == "ffffffffffffffffffffffffffffffff80000000000000000000000000000000")
    val zero136 = new Int136(BigInt(0))
    assert(NumericType.encode(zero136) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max136 = new Int136(BigInt("43556142965880123323311949751266331066367"))
    assert(NumericType.encode(max136) == "0000000000000000000000000000007fffffffffffffffffffffffffffffffff")
    val min136 = new Int136(BigInt("-43556142965880123323311949751266331066368"))
    assert(NumericType.encode(min136) == "ffffffffffffffffffffffffffffff8000000000000000000000000000000000")
    val zero144 = new Int144(BigInt(0))
    assert(NumericType.encode(zero144) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max144 = new Int144(BigInt("11150372599265311570767859136324180752990207"))
    assert(NumericType.encode(max144) == "00000000000000000000000000007fffffffffffffffffffffffffffffffffff")
    val min144 = new Int144(BigInt("-11150372599265311570767859136324180752990208"))
    assert(NumericType.encode(min144) == "ffffffffffffffffffffffffffff800000000000000000000000000000000000")
    val zero152 = new Int152(BigInt(0))
    assert(NumericType.encode(zero152) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max152 = new Int152(BigInt("2854495385411919762116571938898990272765493247"))
    assert(NumericType.encode(max152) == "000000000000000000000000007fffffffffffffffffffffffffffffffffffff")
    val min152 = new Int152(BigInt("-2854495385411919762116571938898990272765493248"))
    assert(NumericType.encode(min152) == "ffffffffffffffffffffffffff80000000000000000000000000000000000000")
    val zero160 = new Int160(BigInt(0))
    assert(NumericType.encode(zero160) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max160 = new Int160(BigInt("730750818665451459101842416358141509827966271487"))
    assert(NumericType.encode(max160) == "0000000000000000000000007fffffffffffffffffffffffffffffffffffffff")
    val min160 = new Int160(BigInt("-730750818665451459101842416358141509827966271488"))
    assert(NumericType.encode(min160) == "ffffffffffffffffffffffff8000000000000000000000000000000000000000")
    val zero168 = new Int168(BigInt(0))
    assert(NumericType.encode(zero168) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max168 = new Int168(BigInt("187072209578355573530071658587684226515959365500927"))
    assert(NumericType.encode(max168) == "00000000000000000000007fffffffffffffffffffffffffffffffffffffffff")
    val min168 = new Int168(BigInt("-187072209578355573530071658587684226515959365500928"))
    assert(NumericType.encode(min168) == "ffffffffffffffffffffff800000000000000000000000000000000000000000")
    val zero176 = new Int176(BigInt(0))
    assert(NumericType.encode(zero176) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max176 = new Int176(BigInt("47890485652059026823698344598447161988085597568237567"))
    assert(NumericType.encode(max176) == "000000000000000000007fffffffffffffffffffffffffffffffffffffffffff")
    val min176 = new Int176(BigInt("-47890485652059026823698344598447161988085597568237568"))
    assert(NumericType.encode(min176) == "ffffffffffffffffffff80000000000000000000000000000000000000000000")
    val zero184 = new Int184(BigInt(0))
    assert(NumericType.encode(zero184) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max184 = new Int184(BigInt("12259964326927110866866776217202473468949912977468817407"))
    assert(NumericType.encode(max184) == "0000000000000000007fffffffffffffffffffffffffffffffffffffffffffff")
    val min184 = new Int184(BigInt("-12259964326927110866866776217202473468949912977468817408"))
    assert(NumericType.encode(min184) == "ffffffffffffffffff8000000000000000000000000000000000000000000000")
    val zero192 = new Int192(BigInt(0))
    assert(NumericType.encode(zero192) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max192 = new Int192(BigInt("3138550867693340381917894711603833208051177722232017256447"))
    assert(NumericType.encode(max192) == "00000000000000007fffffffffffffffffffffffffffffffffffffffffffffff")
    val min192 = new Int192(BigInt("-3138550867693340381917894711603833208051177722232017256448"))
    assert(NumericType.encode(min192) == "ffffffffffffffff800000000000000000000000000000000000000000000000")
    val zero200 = new Int200(BigInt(0))
    assert(NumericType.encode(zero200) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max200 = new Int200(BigInt("803469022129495137770981046170581301261101496891396417650687"))
    assert(NumericType.encode(max200) == "000000000000007fffffffffffffffffffffffffffffffffffffffffffffffff")
    val min200 = new Int200(BigInt("-803469022129495137770981046170581301261101496891396417650688"))
    assert(NumericType.encode(min200) == "ffffffffffffff80000000000000000000000000000000000000000000000000")
    val zero208 = new Int208(BigInt(0))
    assert(NumericType.encode(zero208) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max208 = new Int208(BigInt("205688069665150755269371147819668813122841983204197482918576127"))
    assert(NumericType.encode(max208) == "0000000000007fffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min208 = new Int208(BigInt("-205688069665150755269371147819668813122841983204197482918576128"))
    assert(NumericType.encode(min208) == "ffffffffffff8000000000000000000000000000000000000000000000000000")
    val zero216 = new Int216(BigInt(0))
    assert(NumericType.encode(zero216) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max216 = new Int216(BigInt("52656145834278593348959013841835216159447547700274555627155488767"))
    assert(NumericType.encode(max216) == "00000000007fffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min216 = new Int216(BigInt("-52656145834278593348959013841835216159447547700274555627155488768"))
    assert(NumericType.encode(min216) == "ffffffffff800000000000000000000000000000000000000000000000000000")
    val zero224 = new Int224(BigInt(0))
    assert(NumericType.encode(zero224) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max224 = new Int224(BigInt("13479973333575319897333507543509815336818572211270286240551805124607"))
    assert(NumericType.encode(max224) == "000000007fffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min224 = new Int224(BigInt("-13479973333575319897333507543509815336818572211270286240551805124608"))
    assert(NumericType.encode(min224) == "ffffffff80000000000000000000000000000000000000000000000000000000")
    val zero232 = new Int232(BigInt(0))
    assert(NumericType.encode(zero232) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max232 = new Int232(BigInt("3450873173395281893717377931138512726225554486085193277581262111899647"))
    assert(NumericType.encode(max232) == "0000007fffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min232 = new Int232(BigInt("-3450873173395281893717377931138512726225554486085193277581262111899648"))
    assert(NumericType.encode(min232) == "ffffff8000000000000000000000000000000000000000000000000000000000")
    val zero240 = new Int240(BigInt(0))
    assert(NumericType.encode(zero240) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max240 = new Int240(BigInt("883423532389192164791648750371459257913741948437809479060803100646309887"))
    assert(NumericType.encode(max240) == "00007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min240 = new Int240(BigInt("-883423532389192164791648750371459257913741948437809479060803100646309888"))
    assert(NumericType.encode(min240) == "ffff800000000000000000000000000000000000000000000000000000000000")
    val zero248 = new Int248(BigInt(0))
    assert(NumericType.encode(zero248) == "0000000000000000000000000000000000000000000000000000000000000000")
    val max248 = new Int248(BigInt("226156424291633194186662080095093570025917938800079226639565593765455331327"))
    assert(NumericType.encode(max248) == "007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
    val min248 = new Int248(BigInt("-226156424291633194186662080095093570025917938800079226639565593765455331328"))
    assert(NumericType.encode(min248) == "ff80000000000000000000000000000000000000000000000000000000000000")
    val minusOne = new SolidityInt(BigInt(-1))
    assert(NumericType.encode(minusOne) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
  }
  //
  //  /*
  //      TODO: Enable once Solidity supports fixed types - see
  //      https://github.com/ethereum/solidity/issues/409
  //
  //      @Test
  //      public void testUfixedEncode() {
  //          Ufixed zero = new Ufixed24x40(BigInt(0));
  //          assert(NumericType.encode(zero) ==
  //                  ("0000000000000000000000000000000000000000000000000000000000000000"));
  //
  //          Ufixed maxLong = new Ufixed24x40(BigInt(Long.MAX_VALUE));
  //          assert(NumericType.encode(maxLong) ==
  //                  ("0000000000000000000000000000000000000000000000007fffffffffffffff"));
  //
  //          Ufixed maxValue = new Ufixed(
  //                  BigInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
  //                          16));
  //          assert(NumericType.encode(maxValue) ==
  //                  ("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"));
  //      }
  //
  //      @Test
  //      public void testFixedEncode() {
  //          Fixed zero = new Fixed24x40(BigInt(0));
  //          assert(NumericType.encode(zero) ==
  //                  ("0000000000000000000000000000000000000000000000000000000000000000"));
  //
  //          Fixed maxLong = new Fixed24x40(BigInt(Long.MAX_VALUE));
  //          assert(NumericType.encode(maxLong) ==
  //                  ("0000000000000000000000000000000000000000000000007fffffffffffffff"));
  //
  //          Fixed minLong = new Fixed24x40(BigInt(Long.MIN_VALUE));
  //          assert(NumericType.encode(minLong) ==
  //                  ("ffffffffffffffffffffffffffffffffffffffffffffffff8000000000000000"));
  //
  //          Fixed minusOne = new Fixed24x40(BigInt(-1));
  //          assert(NumericType.encode(minusOne) ==
  //                  ("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"));
  //      }
  //      */
  //      @Test def
  test("StaticBytes") {
    val staticBytes = new Bytes6(Array[Byte](0, 1, 2, 3, 4, 5))
    assert(Bytes.encode(staticBytes) == "0001020304050000000000000000000000000000000000000000000000000000")
    val empty = new Bytes1(Array[Byte](0))
    assert(Bytes.encode(empty) == "0000000000000000000000000000000000000000000000000000000000000000")
    val ones = new Bytes1(Array[Byte](127))
    assert(Bytes.encode(ones) == "7f00000000000000000000000000000000000000000000000000000000000000")
    val dave = new Bytes4("dave".getBytes)
    assert(Bytes.encode(dave) == "6461766500000000000000000000000000000000000000000000000000000000")
  }

  test("DynamicBytes") {
    val dynamicBytes = new DynamicBytes(Array[Byte](0, 1, 2, 3, 4, 5))
    assert(DynamicBytes.encode(dynamicBytes) == "0000000000000000000000000000000000000000000000000000000000000006" + "0001020304050000000000000000000000000000000000000000000000000000")
    val empty = new DynamicBytes(Array[Byte](0))
    assert(DynamicBytes.encode(empty) == "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000")
    val dave = new DynamicBytes("dave".getBytes)
    assert(DynamicBytes.encode(dave) == "0000000000000000000000000000000000000000000000000000000000000004" + "6461766500000000000000000000000000000000000000000000000000000000")
    val loremIpsum = new DynamicBytes(("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " + "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " + "ea commodo consequat. Duis aute irure dolor in reprehenderit in " + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " + "sint occaecat cupidatat non proident, sunt in culpa qui officia " + "deserunt mollit anim id est laborum.").getBytes)
    assert(DynamicBytes.encode(loremIpsum) == "00000000000000000000000000000000000000000000000000000000000001bd" + "4c6f72656d20697073756d20646f6c6f722073697420616d65742c20636f6e73" + "656374657475722061646970697363696e6720656c69742c2073656420646f20" + "656975736d6f642074656d706f7220696e6369646964756e74207574206c6162" + "6f726520657420646f6c6f7265206d61676e6120616c697175612e2055742065" + "6e696d206164206d696e696d2076656e69616d2c2071756973206e6f73747275" + "6420657865726369746174696f6e20756c6c616d636f206c61626f726973206e" + "69736920757420616c697175697020657820656120636f6d6d6f646f20636f6e" + "7365717561742e2044756973206175746520697275726520646f6c6f7220696e" + "20726570726568656e646572697420696e20766f6c7570746174652076656c69" + "7420657373652063696c6c756d20646f6c6f726520657520667567696174206e" + "756c6c612070617269617475722e204578636570746575722073696e74206f63" + "63616563617420637570696461746174206e6f6e2070726f6964656e742c2073" + "756e7420696e2063756c706120717569206f666669636961206465736572756e" + "74206d6f6c6c697420616e696d20696420657374206c61626f72756d2e000000")
  }
  test("Address") {
      val address = new Address("0xbe5422d15f39373eb0a97ff8c10fbd0e40e29338")
      assert(address.getTypeAsString == "address")
      assert(Address.encode(address) == "000000000000000000000000be5422d15f39373eb0a97ff8c10fbd0e40e29338")
    }

  test("InvalidAddress") {
    assertThrows[IllegalArgumentException]{
      new Address("0xa04462684b510796c186d19abfa6929742f79394583d6efb1243bbb473f21d9f")
    }
  }

   test("LongAddress")  {
      val address = new Address(256, "0xa04462684b510796c186d19abfa6929742f79394583d6efb1243bbb473f21d9f")
      assert(address.getTypeAsString == "address")
      Address.encode(address)
    }

     test("Utf8String") {
      val string = new Utf8String("Hello, world!")
      assert(Utf8String.encode(string) == "000000000000000000000000000000000000000000000000000000000000000d" + "48656c6c6f2c20776f726c642100000000000000000000000000000000000000")
    }
  //
  //  @Test def testFixedArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt(BigInt(0x2) == BigInt(0x2)) == new SolidityUInt(BigInt(0x8) == BigInt(0x8)))
  //    assert(TypeEncoder.encodeArrayValues(array) == "0000000000000000000000000000000220000000000000000000000000000000" + "0000000000000000000000000000000880000000000000000000000000000000")
  //  }
  //
  //  @Test def testDynamicArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt(BigIntteger.ONE) == new SolidityUInt(BigInt(2)) == new SolidityUInt(BigInt(3)))
  //    assert(TypeEncoder.encodeDynamicArray(array) == "0000000000000000000000000000000000000000000000000000000000000003" + "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000002" + "0000000000000000000000000000000000000000000000000000000000000003")
  //  }
  //
  //  @Test def testDynamicStringsArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt("web3j") == new SolidityUInt("arrays") == new SolidityUInt("encoding"))
  //    assert("0000000000000000000000000000000000000000000000000000000000000003" + "0000000000000000000000000000000000000000000000000000000000000060" + "00000000000000000000000000000000000000000000000000000000000000a0" + "00000000000000000000000000000000000000000000000000000000000000e0" + "0000000000000000000000000000000000000000000000000000000000000005" + "776562336a000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000006" + "6172726179730000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000008" + "656e636f64696e67000000000000000000000000000000000000000000000000", TypeEncoder.encodeDynamicArray(array))
  //  }
  //
  //  @Test def testStructsDynamicArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt("id", "name") == new SolidityUInt("id", "name") == new SolidityUInt("id", "name"))
  //    assert(TypeEncoder.encodeDynamicArray(array) == "0000000000000000000000000000000000000000000000000000000000000003" + "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000120" + "00000000000000000000000000000000000000000000000000000000000001e0" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "0000000000000000000000000000000000000000000000000000000000000002" + "6964000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000004" + "6e616d6500000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "0000000000000000000000000000000000000000000000000000000000000002" + "6964000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000004" + "6e616d6500000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "0000000000000000000000000000000000000000000000000000000000000002" + "6964000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000004" + "6e616d6500000000000000000000000000000000000000000000000000000000")
  //  }
  //
  //  @Test def testDynamicStructStaticArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt("", "") == new SolidityUInt("id", "name") == new SolidityUInt("", ""))
  //    assert("0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000080" + "0000000000000000000000000000000000000000000000000000000000000002" + "6964000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000004" + "6e616d6500000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000000", TypeEncoder.encodeArrayValues(array))
  //  }
  //
  //  @Test def testStaticStructStaticArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt], new SolidityUInt(BigIntteger.ONE, BigInt(0)) == new SolidityUInt(BigIntteger.ONE, BigInt(0)) == new SolidityUInt(BigIntteger.ONE, BigInt(0)))
  //    assert(TypeEncoder.encodeArrayValues(array) == "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000001" + "0000000000000000000000000000000000000000000000000000000000000000")
  //  }
  //
  //  @SuppressWarnings(Array("unchecked"))
  //  @Test def testEmptyArray() = {
  //    val array = new SolidityUInt(classOf[SolidityUInt])
  //    assert(TypeEncoder.encodeDynamicArray(array) == "0000000000000000000000000000000000000000000000000000000000000000")
  //  }
  //
  //  @SuppressWarnings(Array("unchecked"))
  //  @Test def testArrayOfBytes() = {
  //    val array = new SolidityUInt(new SolidityUInt(Numeric.hexStringToByteArray("0x3c329ee8cd725a7f74f984cac52598eb170d731e7f3" + "80d59a18aa861d2c8d6c43c880b2bfe0f3cde4efcd7" + "11c010c2f1d8af5e796f06716539446f95420df4211c")) == new SolidityUInt(Numeric.hexStringToByteArray("0xcafe0000cafe0000cafe0000")) == new SolidityUInt(Numeric.hexStringToByteArray("0x9215c928b97e0ebeeefd10003a4e3eea23f2eb3acba" + "b477eeb589d7a8874d7c5")))
  //    val emptyArray = DynamicArray.empty("bytes")
  //    val arrayOfEmptyBytes = new SolidityUInt(new SolidityUInt(new Array[Byte](0)) == new SolidityUInt(new Array[Byte](0)))
  //    assert(TypeEncoder.encodeDynamicArray(array) == //  array length
  //      "0000000000000000000000000000000000000000000000000000000000000003" + // offset first bytes
  //        "0000000000000000000000000000000000000000000000000000000000000060" + // offset second bytes
  //        "00000000000000000000000000000000000000000000000000000000000000e0" + // offset third bytes
  //        "0000000000000000000000000000000000000000000000000000000000000120" + // length first bytes
  //        "0000000000000000000000000000000000000000000000000000000000000041" + // first bytes
  //        "3c329ee8cd725a7f74f984cac52598eb170d731e7f380d59a18aa861d2c8d6c4" + // first bytes continued
  //        "3c880b2bfe0f3cde4efcd711c010c2f1d8af5e796f06716539446f95420df421" + "1c00000000000000000000000000000000000000000000000000000000000000" + // length second bytes
  //        "000000000000000000000000000000000000000000000000000000000000000c" + // second bytes
  //        "cafe0000cafe0000cafe00000000000000000000000000000000000000000000" + // length third bytes
  //        "0000000000000000000000000000000000000000000000000000000000000020" + // third bytes
  //        "9215c928b97e0ebeeefd10003a4e3eea23f2eb3acbab477eeb589d7a8874d7c5")
  //    assert(TypeEncoder.encodeDynamicArray(emptyArray) == "0000000000000000000000000000000000000000000000000000000000000000")
  //    assert(TypeEncoder.encodeDynamicArray(arrayOfEmptyBytes) == "0000000000000000000000000000000000000000000000000000000000000002" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000000")
  //  }
  //
  //  @SuppressWarnings(Array("unchecked"))
  //  @Test def testArrayOfStrings() = {
  //    val array = new SolidityUInt(new SolidityUInt("This string value is extra long so that it " + "requires more than 32 bytes") == new SolidityUInt("abc") == new SolidityUInt("") == new SolidityUInt("web3j"))
  //    val emptyArray = DynamicArray.empty("string")
  //    val arrayOfEmptyStrings = new SolidityUInt(new SolidityUInt("") == new SolidityUInt(""))
  //    assert(TypeEncoder.encodeDynamicArray(array) == "0000000000000000000000000000000000000000000000000000000000000004" + // offset first string
  //      "0000000000000000000000000000000000000000000000000000000000000080" + // offset second string
  //      "0000000000000000000000000000000000000000000000000000000000000100" + // offset third string
  //      "0000000000000000000000000000000000000000000000000000000000000140" + // offset fourth string
  //      "0000000000000000000000000000000000000000000000000000000000000160" + // length first string
  //      "0000000000000000000000000000000000000000000000000000000000000046" + // first string
  //      "5468697320737472696e672076616c7565206973206578747261206c6f6e6720" + // first string continued
  //      "736f2074686174206974207265717569726573206d6f7265207468616e203332" + "2062797465730000000000000000000000000000000000000000000000000000" + // length second string
  //      "0000000000000000000000000000000000000000000000000000000000000003" + // second string
  //      "6162630000000000000000000000000000000000000000000000000000000000" + // length third string
  //      "0000000000000000000000000000000000000000000000000000000000000000" + // length fourth string
  //      "0000000000000000000000000000000000000000000000000000000000000005" + // fourth string
  //      "776562336a000000000000000000000000000000000000000000000000000000")
  //    assert(TypeEncoder.encodeDynamicArray(emptyArray) == "0000000000000000000000000000000000000000000000000000000000000000")
  //    assert(TypeEncoder.encodeDynamicArray(arrayOfEmptyStrings) == "0000000000000000000000000000000000000000000000000000000000000002" + "0000000000000000000000000000000000000000000000000000000000000040" + "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000000")
  //  }
  //
    test("PrimitiveByte")  {
      assert(PrimitiveType.encode(PrimitiveByte(0.toByte)) == "0000000000000000000000000000000000000000000000000000000000000000")
      assert(PrimitiveType.encode(PrimitiveByte(127.toByte)) == "7f00000000000000000000000000000000000000000000000000000000000000")
    }

   test("PrimitiveChar")  {
      assert(PrimitiveType.encode( PrimitiveChar('a')) == "0000000000000000000000000000000000000000000000000000000000000001" + "6100000000000000000000000000000000000000000000000000000000000000")
      assert(PrimitiveType.encode( PrimitiveChar(' ')) == "0000000000000000000000000000000000000000000000000000000000000001" + "2000000000000000000000000000000000000000000000000000000000000000")
    }

   test("PrimitiveInt")  {
      assert(PrimitiveType.encode(PrimitiveInt(0)) == "0000000000000000000000000000000000000000000000000000000000000000")
      assert(PrimitiveType.encode(PrimitiveInt(Int.MinValue)) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffff80000000")
      assert(PrimitiveType.encode(PrimitiveInt(Int.MaxValue)) == "000000000000000000000000000000000000000000000000000000007fffffff")
    }

   test("PrimitiveShort")  {
      assert(PrimitiveType.encode(PrimitiveShort(0.toShort)) == "0000000000000000000000000000000000000000000000000000000000000000")
      assert(PrimitiveType.encode(PrimitiveShort(Short.MinValue)) == "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8000")
      assert(PrimitiveType.encode(PrimitiveShort(Short.MaxValue)) == "0000000000000000000000000000000000000000000000000000000000007fff")
    }

    test("PrimitiveLong")  {
      assert(PrimitiveType.encode(PrimitiveLong(0)) == "0000000000000000000000000000000000000000000000000000000000000000")
      assert(PrimitiveType.encode(PrimitiveLong(Long.MinValue)) == "ffffffffffffffffffffffffffffffffffffffffffffffff8000000000000000")
      assert(PrimitiveType.encode(PrimitiveLong(Long.MaxValue)) == "0000000000000000000000000000000000000000000000007fffffffffffffff")
    }

   test("PrimitiveFloat") {
     assertThrows[UnsupportedOperationException] {
       PrimitiveType.encode(PrimitiveFloat(0))
     }
   }

   test("PrimitiveDouble") {
     assertThrows[UnsupportedOperationException] {
       PrimitiveType.encode(PrimitiveDouble(0))
     }
   }

  //  @Test def testStructContainingDynamicBytes() = {
  //    val expectedEncoding = "0000000000000000000000000000000000000000000000000000000000000060" + "0000000000000000000000000000000000000000000000000000000000000000" + "00000000000000000000000000000000000000000000000000000000000000a0" + "0000000000000000000000000000000000000000000000000000000000000007" + "64796e616d696300000000000000000000000000000000000000000000000000" + "0000000000000000000000000000000000000000000000000000000000000005" + "4279746573000000000000000000000000000000000000000000000000000000"
  //    assert(expectedEncoding, encode(AbiV2TestFixture.addDynamicBytesArrayFunction.getInputParameters.get(0)))
  //  }
end TypeEncoderTest
