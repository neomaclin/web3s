package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbPut = Response[Boolean]

object DbPut:
  def apply(response: Response[Boolean]): DbPut = response

extension (x: DbPut)
  def isValueStored: Boolean = x.result

