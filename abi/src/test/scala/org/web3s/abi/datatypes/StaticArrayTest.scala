
package org.web3s.abi.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.codec.TypeAsStringConverter
import org.web3s.abi.datatypes.Address
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.codec.strings.given

class StaticArrayTest extends AnyFunSuite :
  test("canBeInstantiatedWithLessThan32Elements") {
    val array = StaticArray32(arrayOfUints(32))
    assert(array.value.size == 32)
  }

  test("canBeInstantiatedWithSizeMatchingType") {
    val array = StaticArray3(arrayOfUints(3))
    assert(array.value.size == 3)
  }
  
  test("throwsIfSizeDoesntMatchType") {
    try
      StaticArray3(arrayOfUints(4))
    catch
      case e: IllegalArgumentException =>
        assert(e.getMessage == "requirement failed: Expected array of type [StaticArray3] to have [3] elements.")
  }

  test("throwsIfSizeIsAboveMaxOf32") {
    try
      StaticArray32(arrayOfUints(33))
    catch
      case e: IllegalArgumentException =>
        assert(e.getMessage == "requirement failed: Static arrays with a length greater than 32 are not supported.")
  }

  test("testEmptyStaticArray") {
    val array = StaticArray0[Address](Nil)
    assert("address[0]" == TypeAsStringConverter.convert(array))
  }
  private def arrayOfUints(length: Int): Seq[UInt8] = (1 to length).map(UInt8(_))

