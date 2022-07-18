package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response

opaque type NewAccountIdentifier = Response[String]

object NewAccountIdentifier:
  def apply(response: Response[String]): NewAccountIdentifier = response

extension (x: NewAccountIdentifier)
  def accountId: String = x.result
