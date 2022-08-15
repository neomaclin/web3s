package org.web3s.protocol.core.methods.request

import org.web3s.utils.Numeric
final case class Transaction(from: String,
                             nonce: Option[BigInt] = None,
                             gasPrice: Option[BigInt] = None,
                             gas: Option[BigInt] = None,
                             to: Option[String] = None,
                             value: Option[BigInt] = None,
                             data: String,
                             chainId: Option[Long] = None,
                             maxPriorityFeePerGas: Option[BigInt] = None,
                             maxFeePerGas: Option[BigInt] = None)

object Transaction:

  def createContractTransaction(from: String, nonce: BigInt, gasPrice: BigInt, init: String): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), None, None, None, Numeric.prependHexPrefix(init))
  def createContractTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, value: Option[BigInt], init: String): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), None, value, Numeric.prependHexPrefix(init))

  def createEtherTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, value: BigInt): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), Some(value), Numeric.prependHexPrefix(""))

  def createFunctionCallTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, value: BigInt, data: String) : Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), Some(value), Numeric.prependHexPrefix(data))

  def createFunctionCallTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, data: String): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), None,  Numeric.prependHexPrefix(data))

  def createEthCallTransaction(from: String, to: String, data: String, weiValue: BigInt): Transaction =
    Transaction(from, None, None, None, Some(to), Some(weiValue),  Numeric.prependHexPrefix(data))

  def createEthCallTransaction(from: String, to: String, data: String): Transaction =
    Transaction(from, None, None, None, Some(to), None,  Numeric.prependHexPrefix(data))
