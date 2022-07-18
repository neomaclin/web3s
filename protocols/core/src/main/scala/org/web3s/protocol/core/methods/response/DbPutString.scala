package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbPutString = Response[Boolean]

object DbPutString:
  def apply(response: Response[Boolean]): DbPutString = response

extension (x: DbPutString)
  def isValueStored: Boolean = x.result

