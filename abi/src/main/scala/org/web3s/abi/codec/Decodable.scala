package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.EthType


trait Decodable[T <: EthType[_]]:
  def decode(data: String, offset: Int): T
