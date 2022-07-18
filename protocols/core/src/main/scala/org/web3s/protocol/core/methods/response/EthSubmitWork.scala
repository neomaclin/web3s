package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSubmitWork = Response[Boolean]

object EthSubmitWork:
  def apply(response: Response[Boolean]): EthSubmitWork = response

extension (x: EthSubmitWork)
  def isSolutionValid: Boolean = x.result
