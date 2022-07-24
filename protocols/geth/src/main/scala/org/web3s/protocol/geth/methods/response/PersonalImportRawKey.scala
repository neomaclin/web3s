package org.web3s.protocol.geth.methods.response


import org.web3s.protocol.core.Response

opaque type PersonalImportRawKey = Response[String]

object PersonalImportRawKey:
  def apply(response: Response[String]): PersonalImportRawKey = response

extension (x: PersonalImportRawKey)
  def accountId: String = x.result
