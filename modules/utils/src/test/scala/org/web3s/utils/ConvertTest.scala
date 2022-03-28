
package org.web3s.utils

import org.scalatest.funsuite.AnyFunSuite

class ConvertTest extends AnyFunSuite :

  test("FromWei") {
    assert(Convert.fromWei("21000000000000", Convert.Unit.WEI) ==  BigDecimal("21000000000000"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.KWEI) ==  BigDecimal("21000000000"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.MWEI) ==  BigDecimal("21000000"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.GWEI) ==  BigDecimal("21000"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.SZABO) ==  BigDecimal("21"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.FINNEY) ==  BigDecimal("0.021"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.ETHER) ==  BigDecimal("0.000021"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.KETHER) ==  BigDecimal("0.000000021"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.METHER) ==  BigDecimal("0.000000000021"))
    assert(Convert.fromWei("21000000000000", Convert.Unit.GETHER) ==  BigDecimal("0.000000000000021"))
  }

  test("ToWei") {
    assert(Convert.toWei("21", Convert.Unit.WEI) ==  BigDecimal("21"))
    assert(Convert.toWei("21", Convert.Unit.KWEI) ==  BigDecimal("21000"))
    assert(Convert.toWei("21", Convert.Unit.MWEI) ==  BigDecimal("21000000"))
    assert(Convert.toWei("21", Convert.Unit.GWEI) ==  BigDecimal("21000000000"))
    assert(Convert.toWei("21", Convert.Unit.SZABO) ==  BigDecimal("21000000000000"))
    assert(Convert.toWei("21", Convert.Unit.FINNEY) ==  BigDecimal("21000000000000000"))
    assert(Convert.toWei("21", Convert.Unit.ETHER) ==  BigDecimal("21000000000000000000"))
    assert(Convert.toWei("21", Convert.Unit.KETHER) ==  BigDecimal("21000000000000000000000"))
    assert(Convert.toWei("21", Convert.Unit.METHER) ==  BigDecimal("21000000000000000000000000"))
    assert(Convert.toWei("21", Convert.Unit.GETHER) ==  BigDecimal("21000000000000000000000000000"))
  }

  test("Unit") {
    assert(Convert.Unit.fromString("ether") == Convert.Unit.ETHER)
    assert(Convert.Unit.fromString("ETHER") == Convert.Unit.ETHER)
    assert(Convert.Unit.fromString("wei") == Convert.Unit.WEI)
  }
end ConvertTest
