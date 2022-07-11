package org.web3s.protocol.core

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.numeric.NonNegative
import org.web3s.utils.Numeric

sealed trait DefaultBlockParameter:
  def value:String

enum DefaultBlockParameterName(val blockName: String) extends DefaultBlockParameter:
  override def value: String = blockName
  case EARLIEST extends DefaultBlockParameterName("earliest")
  case LATEST extends DefaultBlockParameterName("latest")
  case PENDING extends DefaultBlockParameterName("pending")

final case class DefaultBlockParameterNumber(blockNumber: BigInt) extends DefaultBlockParameter:
  override def value: String = Numeric.encodeQuantity(blockNumber)

object DefaultBlockParameter:
  def valueOf(blockNumber: BigInt Refined NonNegative): DefaultBlockParameter = DefaultBlockParameterNumber(blockNumber.value)
  def valueOf(blockNumber: Long Refined NonNegative): DefaultBlockParameter = DefaultBlockParameterNumber(BigInt(blockNumber.value))
  def valueOf(blockName: String): DefaultBlockParameter = DefaultBlockParameterName.valueOf(blockName)

