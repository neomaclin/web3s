package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
package object admin {
  opaque type AdminDataDir = Response[String]
  opaque type AdminNodeInfo = Response[NodeInfo]

  final case class NodeInfo(
                             enode: String,
                             id: String,
                             ip: String,
                             listenAddr: String,
                             name: String,
                             consensus: String
                           )

  final case class PeerNetwork(localAddress: String, 
                               remoteAddress: String)

  final case class Peer(id: String, 
                        name: String, 
                        enode: String, 
                        network: PeerNetwork) 
    
  opaque type AdminPeers = Response[String]
}
