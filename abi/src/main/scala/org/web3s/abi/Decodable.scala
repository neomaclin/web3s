package org.web3s.abi

import izumi.reflect.Tag
import org.web3s.abi.datatypes.SolidityType

trait Decodable[+T <: SolidityType[_]:Tag]:
  def decode(data: String, offset: Int): T
