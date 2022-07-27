package org.web3s.protocol.parity.methods.response

import org.web3s.protocol.core.Response

opaque type ParityAddressesResponse = Response[List[String]]

object ParityAddressesResponse:
  
  extension (x: ParityAddressesResponse)
    def addresses: List[String] = x.result

  def apply(response: Response[List[String]]): ParityAddressesResponse = response

