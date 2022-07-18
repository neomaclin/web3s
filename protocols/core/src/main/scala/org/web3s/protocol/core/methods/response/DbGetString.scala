package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type DbGetString = Response[String]

object DbGetString:
  def apply(response: Response[String]): DbGetString = response

extension (x: DbGetString)
  def storedValue: String = x.result
