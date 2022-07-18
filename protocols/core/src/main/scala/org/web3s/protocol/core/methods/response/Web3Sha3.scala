package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type Web3Sha3 = Response[String]

object Web3Sha3:
  def apply(response: Response[String]): Web3Sha3 = response
