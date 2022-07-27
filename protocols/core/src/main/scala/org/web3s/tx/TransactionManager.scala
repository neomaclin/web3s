package org.web3s.tx

import cats.MonadThrow
import org.web3s.Web3sEthereum
import org.web3s.protocol.core.DefaultBlockParameter
import org.web3s.protocol.core.methods.response.{EthGetCode, EthSendTransaction}
import org.web3s.protocol.core.methods.response.model.TransactionReceipt

trait TransactionManager[F[_]: MonadThrow]:
  def fromAddress: F[String]
  
  def executeTransaction(gasPrice: BigInt,
                         gasLimit: BigInt,
                         to: String,
                         data: String,
                         value: BigInt,
                         constructor: Boolean = false):F[TransactionReceipt]

  def sendTransaction(gasPrice: BigInt,
                         gasLimit: BigInt,
                         to: String,
                         data: String,
                         value: BigInt,
                         constructor: Boolean = false): F[EthSendTransaction]
  def executeTransactionEIP1559(chainId: Long,
                                maxPriorityFeePerGas: BigInt,
                                maxFeePerGas: BigInt,
                                gasLimit: BigInt,
                                to: String,
                                data: String,
                                value: BigInt,
                                constructor: Boolean = false):F[TransactionReceipt]

  def sendEIP1559Transaction(chainId: Long,
                                maxPriorityFeePerGas: BigInt,
                                maxFeePerGas: BigInt,
                                gasLimit: BigInt,
                                to: String,
                                data: String,
                                value: BigInt,
                                constructor: Boolean = false): F[EthSendTransaction]


  def sendCall(to: String, data: String, defaultBlockParameter: DefaultBlockParameter):F[String]

  def getCode(contractAddress: String, defaultBlockParameter: DefaultBlockParameter):F[EthGetCode]


object TransactionManager:
  val DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH = 40
  val DEFAULT_POLLING_FREQUENCY = Web3sEthereum.DEFAULT_BLOCK_TIME
  val REVERT_ERR_STR = "Contract Call has been reverted by the EVM with the reason: '%s'."
