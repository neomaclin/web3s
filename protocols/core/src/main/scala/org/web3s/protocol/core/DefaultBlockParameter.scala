package org.web3s.protocol.core

import org.web3s.utils.Numeric

sealed trait DefaultBlockParameter:
  def value:String

enum DefaultBlockParameterName(private val blockName: String) extends DefaultBlockParameter:
  override def value: String = blockName
  case EARLIEST extends DefaultBlockParameterName("earliest")
  case LATEST extends DefaultBlockParameterName("latest")
  case PENDING extends DefaultBlockParameterName("pending")

final case class DefaultBlockParameterNumber(private val blockNumber: BigInt) extends DefaultBlockParameter:
  override def value: String = Numeric.encodeQuantity(blockNumber)

object DefaultBlockParameter:
  def valueOf(blockNumber: BigInt): DefaultBlockParameter = DefaultBlockParameterNumber(blockNumber)
  def valueOf(blockNumber: Long): DefaultBlockParameter = DefaultBlockParameterNumber(BigInt(blockNumber))
  def valueOf(blockName: String): DefaultBlockParameter = DefaultBlockParameterName.valueOf(blockName.toUpperCase)

