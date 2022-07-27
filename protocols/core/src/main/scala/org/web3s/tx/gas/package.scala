package org.web3s.tx

package gas:

  sealed trait ContractGasProvider:
    def gasPrice: BigInt

    def gasLimit: BigInt

  sealed trait ContractEIP1559GasProvider extends ContractGasProvider :
    def isEIP1559Enabled: Boolean

    def chainId: Long

    def maxFeePerGas: BigInt

    def maxPriorityFeePerGas: BigInt


  final case class StaticGasProvider(gasPrice: BigInt = BigInt(4100000000L),
                                     gasLimit: BigInt = BigInt(9000000)) extends ContractGasProvider


  final case class StaticEIP1559GasProvider(chainId: Long,
                                            maxFeePerGas: BigInt,
                                            maxPriorityFeePerGas: BigInt,
                                            gasLimit: BigInt
                                           ) extends ContractEIP1559GasProvider :
    def isEIP1559Enabled: Boolean = true

    override def gasPrice: BigInt = maxFeePerGas


  object ContractGasProvider:
    val default: ContractGasProvider = StaticGasProvider()

