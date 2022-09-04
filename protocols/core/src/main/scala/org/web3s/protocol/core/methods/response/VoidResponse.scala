package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type VoidResponse = Response[Unit]

object VoidResponse:
  def apply(response: Response[Unit]): VoidResponse = response

