package org.web3s.protocol.besu.methods.response.model

import org.web3s.protocol.eea.util.*

final case class PrivacyGroup(privacyGroupId: String,
                              `type`: PrivacyGroupType,
                              name: Option[String],
                              description: Option[String],
                              members: List[Base64String])

enum PrivacyGroupType:
  case LEGACY, PANTHEON, ONCHAIN
