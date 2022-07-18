package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbGetHex = Response[String]

object DbGetHex:
  def apply(response: Response[String]): DbGetHex = response

extension (x: DbGetHex)
  def storedValue: String = x.result
