package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type NetListening = Response[Boolean]

object NetListening:
  def apply(response: Response[Boolean]): NetListening = response

extension (x: NetListening)
  def isListening: Boolean = x.result
