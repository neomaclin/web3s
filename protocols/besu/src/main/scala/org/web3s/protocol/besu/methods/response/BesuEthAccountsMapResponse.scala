package org.web3s.protocol.besu.methods.response

import org.web3s.protocol.core.Response

opaque type BesuEthAccountsMapResponse = Response[Map[String, Boolean]]

object BesuEthAccountsMapResponse:
  def apply(response: Response[Map[String, Boolean]]): BesuEthAccountsMapResponse = response

extension (x: BesuEthAccountsMapResponse)
  def accounts: Map[String, Boolean] = x.result

