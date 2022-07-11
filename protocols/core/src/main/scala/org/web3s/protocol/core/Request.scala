package org.web3s.protocol.core

import java.util.concurrent.atomic.AtomicLong

final case class Request[S](id:Long = Request.nextId.getAndIncrement(),
                            jsonrpc: String = "2.0",
                            method: Request.Method,
                            params: List[S] = Nil)

object Request:
  private val nextId = new AtomicLong(0)
  trait Method:
    def name: String