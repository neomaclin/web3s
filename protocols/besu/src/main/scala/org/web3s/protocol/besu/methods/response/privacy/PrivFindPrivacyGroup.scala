package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.besu.methods.response.model.PrivacyGroup
import org.web3s.protocol.core.Response

opaque type PrivFindPrivacyGroup = Response[List[PrivacyGroup]]

object PrivFindPrivacyGroup:

  def apply(responses: Response[List[PrivacyGroup]]): PrivFindPrivacyGroup = responses

extension (x: PrivFindPrivacyGroup)
  def groups: List[PrivacyGroup] = x.result
