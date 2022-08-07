package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, EthType}
import izumi.reflect.Tag

final class StaticArray18[T <: EthType[_] : Tag](override val value: Seq[T]) extends StaticArray(18, value)
