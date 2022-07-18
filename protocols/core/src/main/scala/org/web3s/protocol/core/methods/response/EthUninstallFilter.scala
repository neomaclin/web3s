package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthUninstallFilter = Response[Boolean]

object EthUninstallFilter:
  def apply(response: Response[Boolean]): EthUninstallFilter = response

extension (x: EthUninstallFilter)
  def isUninstalled: Boolean = x.result
