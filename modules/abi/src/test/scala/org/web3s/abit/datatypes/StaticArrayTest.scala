
package org.web3s.abit.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.Address
import org.web3s.abi.datatypes.generated.*

class StaticArrayTest extends AnyFunSuite :
  test("canBeInstantiatedWithLessThan32Elements") {
    val array = new StaticArray32(arrayOfUints(32))
    assert(array.values.size == 32)
  }

  test("canBeInstantiatedWithSizeMatchingType") {
    val array = new StaticArray3(arrayOfUints(3))
    assert(array.values.size == 3)
  }
  //
  test("throwsIfSizeDoesntMatchType") {
    try
      new StaticArray3(arrayOfUints(4))
    catch
      case e: IllegalArgumentException =>
        assert(e.getMessage == "requirement failed: Expected array of type [StaticArray3] to have [3] elements.")
  }

  test("throwsIfSizeIsAboveMaxOf32") {
    try
      new StaticArray32(arrayOfUints(33))
    catch
      case e: IllegalArgumentException =>
        assert(e.getMessage == "requirement failed: Static arrays with a length greater than 32 are not supported.")
  }

  test("testEmptyStaticArray") {
    val array = new StaticArray0[Address](Nil)
    assert(Address.TYPE_NAME + "[0]" == array.getTypeAsString)
  }

  private def arrayOfUints(length: Int): List[UInt8] = (1 to length).map(new UInt8(_)).toList
end StaticArrayTest
