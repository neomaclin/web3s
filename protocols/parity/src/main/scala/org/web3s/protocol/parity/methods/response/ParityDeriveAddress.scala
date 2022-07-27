package org.web3s.protocol.parity.methods.response

import org.web3s.protocol.core.Response

opaque type ParityDeriveAddress = Response[String]

object ParityDeriveAddress:
  extension (x: ParityDeriveAddress)
    def address: String = x.result

  def apply(response: Response[String]): ParityDeriveAddress = response

