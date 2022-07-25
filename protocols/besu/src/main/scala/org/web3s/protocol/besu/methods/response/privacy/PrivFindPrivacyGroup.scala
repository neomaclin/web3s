package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response
import org.web3s.protocol.eea.util.Base64String

opaque type PrivFindPrivacyGroup = Response[List[PrivFindPrivacyGroup.PrivacyGroup]]

object PrivFindPrivacyGroup:
  extension (x: PrivFindPrivacyGroup)
    def groups: List[PrivFindPrivacyGroup.PrivacyGroup] = x.result

  def apply(responses: Response[List[PrivacyGroup]]): PrivFindPrivacyGroup = responses

  final case class PrivacyGroup(privacyGroupId: String,
                                 `type`: PrivacyGroupType,
                                 name: String,
                                 description: String,
                                 members: List[Base64String])
  enum PrivacyGroupType:
    case LEGACY, PANTHEON, ONCHAIN


