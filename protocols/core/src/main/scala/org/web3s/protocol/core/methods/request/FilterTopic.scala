package org.web3s.protocol.core.methods.request

sealed trait FilterTopic[+T]

case object NullTopic extends FilterTopic[Nothing]

final case class SingleTopic(value: String) extends FilterTopic[String]

final case class ListTopic(value: List[NullTopic.type | SingleTopic]) extends FilterTopic[List[NullTopic.type | SingleTopic]]
