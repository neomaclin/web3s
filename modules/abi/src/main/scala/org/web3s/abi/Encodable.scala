package org.web3s.abi

import org.web3s.abi.datatypes.SolidityType

trait Encodable[T <: SolidityType[_]]:
  def encode(value: T): String




