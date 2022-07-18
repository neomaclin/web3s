package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthMining = Response[Boolean]

object EthMining:
  def apply(response: Response[Boolean]): EthMining = response

extension (x: EthMining)
  def isMining: Boolean = x.result

