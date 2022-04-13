package org.web3s.abi.datatypes

final case class SolidityEvent[T <: SolidityType[_]](name: String, parameters: List[T])