package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type MinerStartResponse = Response[Unit]

object MinerStartResponse:
  def apply(response: Response[Unit]): MinerStartResponse = response

