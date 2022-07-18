package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response

opaque type PersonalListAccounts = Response[List[String]]

object PersonalListAccounts:
  def apply(response: Response[List[String]]): PersonalListAccounts = response

extension (x: PersonalListAccounts)
  def accountIds: List[String] = x.result
