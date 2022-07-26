package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response

opaque type PrivateEnclaveKey = Response[String]

object PrivateEnclaveKey:

  extension (x: PrivateEnclaveKey)
    def key: String = x.result

  def apply(response: Response[String]): PrivateEnclaveKey = response

