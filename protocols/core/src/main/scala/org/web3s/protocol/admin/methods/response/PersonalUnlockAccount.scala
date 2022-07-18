package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response

opaque type PersonalUnlockAccount = Response[Boolean]

object PersonalUnlockAccount:
  def apply(response: Response[Boolean]): PersonalUnlockAccount = response

extension (x: PersonalUnlockAccount)
  def isAccountUnlocked: Boolean = x.result
