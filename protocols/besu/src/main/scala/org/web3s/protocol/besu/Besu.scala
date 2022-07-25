//package org.web3s.protocol.besu
//
//import org.web3s.protocol.core.methods.response.*
//import org.web3s.protocol.besu.methods.response.*
//import org.web3s.protocol.core.methods.request.*
//
//trait Besu[F[_]]:
//  def minerStart: F[MinerStartResponse]
//
//  def minerStop: F[BooleanResponse]
//
//  def cliqueDiscard(address: String): F[BooleanResponse]
//
//  def cliqueGetSigners(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]
//
//  def cliqueGetSignersAtHash(blockHash: String): F[EthAccounts]
//
//  def cliquePropose(address: String, signerAddition: Boolean): F[BooleanResponse]
//
//  def cliqueProposals: F[BesuEthAccountsMapResponse]
//
//  def debugTraceTransaction(transactionHash: String, options: Map[String, Boolean]): F[BesuFullDebugTraceResponse]
//
//  def ibftDiscardValidatorVote(address: String): F[BooleanResponse]
//
//  def ibftGetPendingVotes: F[BesuEthAccountsMapResponse]
//
//  def ibftGetSignerMetrics: F[BesuSignerMetrics]
//
//  def ibftGetValidatorsByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]
//
//  def ibftGetValidatorsByBlockHash(blockHash: String): F[EthAccounts]
//
//  def ibftProposeValidatorVote(address: String, validatorAddition: Boolean): F[BooleanResponse]
//
//  def privGetTransactionCount(address: String, privacyGroupId: Base64String): F[EthGetTransactionCount]
//
//  def privGetPrivateTransaction(transactionHash: String): F[PrivGetPrivateTransaction]
//
//  def privDistributeRawTransaction(signedTransactionData: String): F[PrivateEnclaveKey]
//
//  def privGetPrivacyPrecompileAddress: F[PrivGetPrivacyPrecompileAddress]
//
//  def privCreatePrivacyGroup(addresses: List[Base64String], name: String, description: String): F[PrivCreatePrivacyGroup]
//
//  @throws[IOException]
//  def privOnChainSetGroupLockState(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, lock: Boolean): F[EthSendTransaction]
//
//  @throws[IOException]
//  @throws[TransactionException]
//  def privOnChainAddToPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]
//
//  @throws[IOException]
//  def privOnChainCreatePrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]
//
//  @throws[IOException]
//  def privOnChainRemoveFromPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participant: Base64String): F[EthSendTransaction]
//
//  def privOnChainFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]
//
//  def privFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]
//
//  def privDeletePrivacyGroup(privacyGroupId: Base64String): F[BooleanResponse]
//
//  def privGetTransactionReceipt(transactionHash: String): F[PrivGetTransactionReceipt]
//
//  def privGetCode(privacyGroupId: String, address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode]
//
//  def privCall(privacyGroupId: String, transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall]
//
//  def privGetLogs(privacyGroupId: String, ethFilter: request.EthFilter): F[EthLog]
//
//  def privNewFilter(privacyGroupId: String, ethFilter: request.EthFilter): F[response.EthFilter]
//
//  def privUninstallFilter(privacyGroupId: String, filterId: String): F[EthUninstallFilter]
//
//  def privGetFilterChanges(privacyGroupId: String, filterId: String): F[EthLog]
//
//  def privGetFilterLogs(privacyGroupId: String, filterId: String): F[EthLog]
//
