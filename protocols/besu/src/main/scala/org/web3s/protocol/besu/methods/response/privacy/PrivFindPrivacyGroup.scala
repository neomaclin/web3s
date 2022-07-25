package org.web3s.protocol.besu.methods.response.privacy

opaque type PrivFindPrivacyGroup = Response[List[PrivFindPrivacyGroup.PrivacyGroup]]

object PrivFindPrivacyGroup:
  final case class PrivacyGroup(privacyGroupId: String,
                                 `type`: PrivacyGroup.Type,
                                 name: String,
                                 description: String,
                                 members: List[Base64String])

  def apply(response: Response[List[PrivacyGroup]]): PrivFindPrivacyGroup = response

extension (x: PrivFindPrivacyGroup)
  def groups: List[PrivFindPrivacyGroup.PrivacyGroup] = x.result

