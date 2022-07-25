package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response
import org.web3s.protocol.eea.util.Base64String

opaque type PrivCreatePrivacyGroup = Response[Base64String]

object PrivCreatePrivacyGroup:

  extension (x: PrivCreatePrivacyGroup)
    def privacyGroupId: Base64String = x.result

  def apply(response: Response[Base64String]): PrivCreatePrivacyGroup = response

