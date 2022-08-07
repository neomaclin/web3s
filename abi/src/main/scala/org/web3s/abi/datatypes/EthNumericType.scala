package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.EthTypes
import org.web3s.abi.datatypes.EthType.MAX_BIT_LENGTH
import org.web3s.utils.Numeric

abstract class EthNumericType(val `type`: String,
                              override val value: BigInt) extends EthType[BigInt]:
  def bitSize: Int

