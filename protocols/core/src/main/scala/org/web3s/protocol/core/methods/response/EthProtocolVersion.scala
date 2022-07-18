package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthProtocolVersion = Response[String]

object EthProtocolVersion:
  def apply(response: Response[String]): EthProtocolVersion = response

extension (x: EthProtocolVersion)
  def protocolVersion: String = x.result

