package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response


opaque type PrivGetPrivacyPrecompileAddress = Response[String]

object PrivGetPrivacyPrecompileAddress:
  def apply(response: Response[String]): PrivGetPrivacyPrecompileAddress = response

extension (x: PrivGetPrivacyPrecompileAddress)
  def address: String = x.result

