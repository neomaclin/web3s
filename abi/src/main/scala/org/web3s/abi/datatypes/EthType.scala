
package org.web3s.abi.datatypes

object EthType:
  val MAX_BIT_LENGTH: Int = 256
  val MAX_BYTE_LENGTH: Int = MAX_BIT_LENGTH / 8

trait EthType[+T]:
  def bytes32PaddedLength: Int = EthType.MAX_BYTE_LENGTH
  def value: T

//
//trait EthSeqType[+T] extends EthType[T]:
//  def values: List[T]
//  override def value: T = values.head
