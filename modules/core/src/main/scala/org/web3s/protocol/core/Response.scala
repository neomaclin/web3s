package org.web3s.protocol.core

final case class Response[T](id: Long, 
                             jsonrpc: String, 
                             result: T, 
                             error: Response.Error,
                             rawResponse: String)
object Response {
  final case class Error( code: Int, message: String, data: String)
}