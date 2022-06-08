package org.web3s.protocol.core

final case class Request[S](id:Long, method: String, params: List[S]) 