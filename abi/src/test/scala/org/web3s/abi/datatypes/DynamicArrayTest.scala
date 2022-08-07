package org.web3s.abi.datatypes

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*

//class DynamicArrayTest extends AnyFunSuite :
//
//    test("EmptyDynamicArray") {
//        val array = new DynamicArray[Address](Nil)
//
//        assert(Address.TYPE_NAME + "[]" == array.getTypeAsString)
//    }
//
////    test("DynamicArrayWithDynamicStruct") {
////        val list = List(new DynamicStruct[Nothing]())
////        val array = new DynamicArray[DynamicStruct[Nothing]](list)
////        assert("()[]" == array.getTypeAsString)
////    }
//
//    test("DynamicArrayWithAbiType") {
//        val array = new DynamicArray[EthUInt](arrayOfuints(1))
//
//        assert(EthUInt.TYPE_NAME + "[]" == array.getTypeAsString)
//    }
//
//
//    private def arrayOfuints(length: Int): Seq[UInt8] = (1 to length).map(new UInt8(_))
