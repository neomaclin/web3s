package org.web3s.abit.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.UInt8

class DynamicArrayTest extends AnyFunSuite :

    test("EmptyDynamicArray") {
        val array = new DynamicArray[Address](Nil)

        assert(Address.TYPE_NAME + "[]" == array.getTypeAsString)
    }

//    test("DynamicArrayWithDynamicStruct") {
//        val list = List(new DynamicStruct[Nothing]())
//        val array = new DynamicArray[DynamicStruct[Nothing]](list)
//        assert("()[]" == array.getTypeAsString)
//    }

    test("DynamicArrayWithAbiType") {
        val array = new DynamicArray[SolidityUInt](arrayOfUints(1))

        assert(SolidityUInt.TYPE_NAME + "[]" == array.getTypeAsString)
    }


    private def arrayOfUints(length: Int): List[UInt8] = (1 to length).map(new UInt8(_)).toList
end DynamicArrayTest

