package org.web3s.protocol.parity.methods.response

import model.Trace
import org.web3s.protocol.core.Response

opaque type ParityTraceGet = Response[Trace]

object ParityTraceGet:
  extension (x: ParityTraceGet)
    def trace: Trace = x.result

  def apply(response: Response[Trace]): ParityTraceGet = response

