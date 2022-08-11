package org.web3s.abi.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.codec.TypeAsStringConverter
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.codec.strings.given
class DynamicArrayTest extends AnyFunSuite :

    test("EmptyDynamicArray") {
        val array = DynamicArray[Address](Nil)

        assert(Address.TYPE_NAME + "[]" == TypeAsStringConverter.convert(array))
    }

    test("DynamicArrayWithDynamicStruct") {
        val list = List(EmptyStruct)
        val array = DynamicArray(list)
        assert("()[]" == TypeAsStringConverter.convert(array))
    }

    test("DynamicArrayWithAbiType") {
        val array = DynamicArray[EthUInt](arrayOfuints(1))

        assert(EthUInt.TYPE_NAME + "[]" ==  TypeAsStringConverter.convert(array))
    }


    private def arrayOfuints(length: Int): Seq[UInt8] = (1 to length).map(UInt8(_))
