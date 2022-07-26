package org.web3s.protocol.besu.methods.request

import org.web3s.protocol.eea.util.Base64String

final case class CreatePrivacyGroupRequest(addresses: List[Base64String],
                                           name: String,
                                           description: String
                                          )