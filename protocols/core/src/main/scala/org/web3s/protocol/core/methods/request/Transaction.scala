package org.web3s.protocol.core.methods.request

final case class Transaction(from: String,
                             nonce: Option[BigInt] = None,
                             gasPrice: Option[BigInt] = None,
                             gasLimit: Option[BigInt] = None,
                             to: Option[String] = None,
                             value: Option[BigInt] = None,
                             data: String,
                             chainId: Option[Long] = None,
                             maxPriorityFeePerGas: Option[BigInt] = None,
                             maxFeePerGas: Option[BigInt] = None)

object Transaction:

  def createContractTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, value: Option[BigInt], init: String): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), None, value, init)

  def createEtherTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, value: BigInt): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), Some(value), "")

  def createFunctionCallTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, value: BigInt, data: String) : Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), Some(value), data)

  def createFunctionCallTransaction(from: String, nonce: BigInt, gasPrice: BigInt, gasLimit: BigInt, to: String, data: String): Transaction =
    Transaction(from, Some(nonce), Some(gasPrice), Some(gasLimit), Some(to), None, data)

  def createEthCallTransaction(from: String, to: String, data: String, weiValue: BigInt): Transaction =
    Transaction(from, None, None, None, Some(to), Some(weiValue), data)

  def createEthCallTransaction(from: String, to: String, data: String): Transaction =
    Transaction(from, None, None, None, Some(to), None, data)
