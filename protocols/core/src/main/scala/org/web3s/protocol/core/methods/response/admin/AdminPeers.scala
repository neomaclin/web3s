package org.web3s.protocol.core.methods.response.admin

import org.web3s.protocol.core.Response

opaque type AdminPeers = Response[List[AdminPeers.Peer]]

object AdminPeers:

  final case class PeerNetwork(localAddress: String,
                               remoteAddress: String)

  final case class Peer(id: String,
                        name: String,
                        enode: String,
                        network: PeerNetwork)

  def apply(response: Response[List[Peer]]): AdminPeers = response