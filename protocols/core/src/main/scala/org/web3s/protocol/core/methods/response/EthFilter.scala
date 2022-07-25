package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric


opaque type EthFilter = Response[EthBigInt]

object EthFilter:

  extension (x: EthFilter)
    def filterId: BigInt = x.result.value

  def apply(response: Response[EthBigInt]): EthFilter = response

