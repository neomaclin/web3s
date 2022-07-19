package org.web3s.protocol.core

import java.util.concurrent.atomic.AtomicLong

import io.circe.Json
final case class Request(id: Long = Request.nextId.getAndIncrement(),
                                     jsonrpc: String = "2.0",
                                     method: String,
                                     params: List[Json] = Nil)

object Request:
  private val nextId = new AtomicLong(0)