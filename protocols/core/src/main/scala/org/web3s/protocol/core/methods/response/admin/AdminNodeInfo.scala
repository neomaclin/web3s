package org.web3s.protocol.core.methods.response.admin

import org.web3s.protocol.core.Response

opaque type AdminNodeInfo = Response[AdminNodeInfo.NodeInfo]

object AdminNodeInfo:
  final case class NodeInfo(
                             enode: String,
                             id: String,
                             ip: String,
                             listenAddr: String,
                             name: String,
                             consensus: String
                           )

  def apply(response: Response[NodeInfo]): AdminNodeInfo = response

