package org.web3s.protocol.core.methods.request

import org.web3s.protocol.core.methods.request.FilterTopic

trait Filter[T]:
  def filter: T
  def topics: List[FilterTopic[_]]
