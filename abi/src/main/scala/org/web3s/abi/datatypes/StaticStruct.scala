
package org.web3s.abi.datatypes

import izumi.reflect.Tag
//
//object StaticStruct:
//  def encode[T <: EthType[_] : Tag : Encodable, S <: StaticStruct[T] ](value: S): String =
//    value.values.map(TypeEncoder.encode[T](_)).mkString
//

class StaticStruct[T <: EthType[_] : Tag](override val value: Seq[T]
                                              ) extends StaticArray[T](value.size, value) with StructType
