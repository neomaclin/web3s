package org.web3s.protocol.parity.methods.response

import org.web3s.protocol.core.Response

opaque type ParityAddressResponse = Response[String]

object ParityAddressResponse:

  def apply(response: Response[String]): ParityAddressResponse = response

extension (x: ParityAddressResponse)
  def address: String = x.result