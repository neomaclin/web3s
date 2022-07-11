package org.web3s.abi.datatypes

import izumi.reflect.Tag


final case class SolidityFunction[T <: SolidityType[_] : Tag](name: String, input: List[T], output: List[Tag[T]])