package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetStorageAt = Response[String]

object EthGetStorageAt:
  def apply(response: Response[String]): EthGetStorageAt = response

extension (x: EthGetStorageAt)
  def data: String = x.result

