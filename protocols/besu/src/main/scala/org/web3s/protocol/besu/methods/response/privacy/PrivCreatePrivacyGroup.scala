package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.eea.util.Base64String
opaque type PrivCreatePrivacyGroup = Response[Base64String]

object PrivCreatePrivacyGroup:
  def apply(response: Response[Base64String]): PrivCreatePrivacyGroup = response

extension (x: PrivCreatePrivacyGroup)
  def privacyGroupId: Base64String = x.result

