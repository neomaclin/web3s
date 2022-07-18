package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type BooleanResponse = Response[Boolean]

object BooleanResponse:
  def apply(response: Response[Boolean]): BooleanResponse = response

extension (x: BooleanResponse)
  def isSuccess: Boolean = x.result
