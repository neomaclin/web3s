package org.web3s.protocol.core

import java.util.concurrent.atomic.AtomicLong
import io.circe.Encoder

final case class Request[S: Encoder](id: Long = Request.nextId.getAndIncrement(),
                                     jsonrpc: String = "2.0",
                                     method: String,
                                     params: List[S] = Nil)

object Request:
  private val nextId = new AtomicLong(0)