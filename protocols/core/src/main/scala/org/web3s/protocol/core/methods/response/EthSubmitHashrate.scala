package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSubmitHashrate = Response[Boolean]

object EthSubmitHashrate:
  def apply(response: Response[Boolean]): EthSubmitHashrate = response

extension (x: EthSubmitHashrate)
  def isSubmissionSuccessful: Boolean = x.result
