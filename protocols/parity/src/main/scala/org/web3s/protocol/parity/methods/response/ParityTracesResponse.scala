package org.web3s.protocol.parity.methods.response

import model.Trace
import org.web3s.protocol.core.Response

opaque type ParityTracesResponse = Response[List[Trace]]

object ParityTracesResponse:
  extension (x: ParityTracesResponse)
    def traces: List[Trace] = x.result

  def apply(response: Response[List[Trace]]): ParityTracesResponse = response

