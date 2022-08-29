package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbGet = Response[String]

object DbGet:
  def apply(response: Response[String]): DbGet = response

extension (x: DbGet)
  def storedValue: String = x.result
