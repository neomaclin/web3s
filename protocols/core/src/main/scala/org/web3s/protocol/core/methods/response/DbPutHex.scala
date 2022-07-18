package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbPutHex = Response[Boolean]

object DbPutHex:
  def apply(response: Response[Boolean]): DbPutHex = response

extension (x: DbPutHex)
  def isValueStored: Boolean = x.result

