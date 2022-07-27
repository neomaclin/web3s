package org.web3s.protocol.parity.methods.response

import org.web3s.protocol.core.Response

opaque type ParityDefaultAddressResponse = Response[String]

object ParityDefaultAddressResponse:
  extension (x: ParityDefaultAddressResponse)
    def address: String = x.result

  def apply(response: Response[String]): ParityDefaultAddressResponse = response

