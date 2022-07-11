package org.web3s.protocol.core.methods.request

final case class ShhFilter(to: String) extends Filter[ShhFilter]:
  override def filter: ShhFilter = this
