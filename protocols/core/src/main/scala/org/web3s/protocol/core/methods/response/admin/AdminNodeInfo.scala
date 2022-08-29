package org.web3s.protocol.core.methods.response.admin

import io.circe.{Decoder, HCursor, Json}
import org.web3s.protocol.core.Response

type AdminNodeInfo = Response[AdminNodeInfo.NodeInfo]

object AdminNodeInfo:

  given Decoder[NodeInfo] = (c: HCursor) =>
    for
      enode <- c.downField("enode").as[String]
      id <- c.downField("id").as[String]
      ip <- c.downField("ip").as[String]
      listenAddr <- c.downField("listenAddr").as[String]
      name <- c.downField("name").as[String]
      consensus <- c.downField("protocols").as[Map[String,Json]].map( map => map.get("eth").flatMap(_.as[Map[String,String]].toOption.flatMap(_.get("consensus"))).orElse(map.keys.headOption).getOrElse("unknown"))
    yield
      NodeInfo(
        enode,
        id,
        ip,
        listenAddr,
        name,
        consensus)

  final case class NodeInfo(
                             enode: String,
                             id: String,
                             ip: String,
                             listenAddr: String,
                             name: String,
                             consensus: String
                           )

  def apply(response: Response[NodeInfo]): AdminNodeInfo = response

