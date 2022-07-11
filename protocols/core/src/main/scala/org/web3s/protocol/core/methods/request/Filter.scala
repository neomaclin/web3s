package org.web3s.protocol.core.methods.request

trait Filter[T]:
  def filter: T

