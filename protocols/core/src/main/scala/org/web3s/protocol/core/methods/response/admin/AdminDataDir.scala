package org.web3s.protocol.core.methods.response.admin

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type AdminDataDir = Response[String]

object AdminDataDir:
  def apply(response: Response[String]): AdminDataDir = response

extension (x: AdminDataDir)
  def dataDir: String = x.result
