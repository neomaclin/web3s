package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response

opaque type PersonalSign = Response[String]

object PersonalSign:
  def apply(response: Response[String]): PersonalSign = response

extension (x: PersonalSign)
  def signedMessage: String = x.result
