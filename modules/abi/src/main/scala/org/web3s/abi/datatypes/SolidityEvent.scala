package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.crypto.Hash
import org.web3s.utils.Numeric

object SolidityEvent:
  def encode[T <: SolidityType[_]:Tag]( event: SolidityEvent[T] ): String =
    val methodSignature = event.name ++ event.parameters.map(_.getTypeAsString).mkString("(" ,",",")") 
    Numeric.toHexString(Hash.sha3(methodSignature.getBytes))
  end encode

end SolidityEvent

final case class SolidityEvent[T <: SolidityType[_]:Tag](name: String, parameters: List[T])