package org.web3s.abi.datatypes

import izumi.reflect.Tag
import Tuple.*

final case class EthFunction[I <: EthType[_] : Tag, O: Tag, T <: Tuple](name: String,
                                                                        input: I *: T,
                                                                        output: List[Tag[O]])