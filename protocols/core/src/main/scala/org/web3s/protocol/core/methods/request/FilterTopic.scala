package org.web3s.protocol.core.methods.request

sealed trait FilterTopic[+T]
case object  NullTopic extends FilterTopic[Nothing]
final case class SingleTopic(value: String) extends FilterTopic[String]
final case class ListTopic[T](value: List[FilterTopic[T]]) extends FilterTopic[List[FilterTopic[T]]]
