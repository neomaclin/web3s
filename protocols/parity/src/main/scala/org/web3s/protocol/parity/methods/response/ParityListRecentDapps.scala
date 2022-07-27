package org.web3s.protocol.parity.methods.response

import org.web3s.protocol.core.Response

opaque type ParityListRecentDapps = Response[List[String]]

object ParityListRecentDapps:
  extension (x: ParityListRecentDapps)
    def dappsIds: List[String] = x.result

  def apply(response: Response[List[String]]): ParityListRecentDapps = response

