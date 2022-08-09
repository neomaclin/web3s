package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.EthType

object TypeEncoder:
  
  inline def encode[T <: EthType[_] : Encodable](value: T): String = summon[Encodable[T]].encode(value)

  inline def encodePacked[T <: EthType[_] : Encodable](value: T): String = summon[Encodable[T]].encodePacked(value)
