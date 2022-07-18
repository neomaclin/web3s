package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type Web3ClientVersion = Response[String]

object Web3ClientVersion:
  def apply(response: Response[String]): Web3ClientVersion = response

extension (x: Web3ClientVersion)
  def web3ClientVersion: String = x.result
