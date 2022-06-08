package org.web3s.protocol.core

trait Web3sService[F[_]] {

  def send[S,T](request: Request[S]):F[Response[T]]

  def sendBatch[S,T](request: List[Request[S]]):F[List[Response[T]]]

 // def subscribe[S,T](request: Request[S], unsubscribeMethod: String): Source[Response[T], Unit]

}
