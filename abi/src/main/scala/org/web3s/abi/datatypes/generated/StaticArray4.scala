package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, EthType}
import izumi.reflect.Tag

final class StaticArray4[T <: EthType[_] : Tag](override val value: Seq[T]) extends StaticArray(4, value)

