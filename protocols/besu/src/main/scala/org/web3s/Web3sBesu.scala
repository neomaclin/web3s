package org.web3s

import cats.MonadThrow
import org.web3s.protocol.core.Request
import org.web3s.protocol.core.methods.response.*
import org.web3s.services.Web3sService

class Web3sBesu[F[_]: MonadThrow](using services: Web3sService[F]) extends Besu[F[_]]:
  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._

  def minerStart: F[MinerStartResponse] =
    services.fetch[String](Request(method = "miner_start")).map(MinerStartResponse.apply)

  def minerStop: F[BooleanResponse] =
    services.fetch[String](Request(method = "miner_stop")).map(BooleanResponse.apply)


  def cliqueDiscard(address: String): F[BooleanResponse]=
    services.fetch[String](Request(method = "clique_discard")).map(BooleanResponse.apply)

  def cliqueGetSigners(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]=
    services.fetch[String](Request(method = "clique_getSigners")).map(EthAccounts.apply)

  def cliqueGetSignersAtHash(blockHash: String): F[EthAccounts]=
    services.fetch[String](Request(method = "clique_getSignersAtHash")).map(EthAccounts.apply)

  def cliquePropose(address: String, signerAddition: Boolean): F[BooleanResponse]=
    services.fetch[String](Request(method = "clique_propose")).map(BooleanResponse.apply)

  def cliqueProposals: F[BesuEthAccountsMapResponse]=
    services.fetch[String](Request(method = "clique_proposals")).map(BesuEthAccountsMapResponse.apply)

  def debugTraceTransaction(transactionHash: String, options: Map[String, Boolean]): F[BesuFullDebugTraceResponse]=
    services.fetch[String](Request(method = "debug_traceTransaction")).map(BesuFullDebugTraceResponse.apply)

  def ibftDiscardValidatorVote(address: String): F[BooleanResponse]=
    services.fetch[String](Request(method = "ibft_discardValidatorVote")).map(BooleanResponse.apply)


  def ibftGetPendingVotes: F[BesuEthAccountsMapResponse] =
    services.fetch[String](Request(method = "ibft_getPendingVotes")).map(BesuEthAccountsMapResponse.apply)

  def ibftGetSignerMetrics: F[BesuSignerMetrics]=
    services.fetch[String](Request(method = "ibft_getSignerMetrics")).map(BesuSignerMetrics.apply)


  def ibftGetValidatorsByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]=
    services.fetch[String](Request(method = "ibft_getValidatorsByBlockNumber")).map(EthAccounts.apply)


  def ibftGetValidatorsByBlockHash(blockHash: String): F[EthAccounts]=
    services.fetch[String](Request(method = "ibft_getValidatorsByBlockHash")).map(EthAccounts.apply)


  def ibftProposeValidatorVote(address: String, validatorAddition: Boolean): F[BooleanResponse]=
    services.fetch[String](Request(method = "ibft_proposeValidatorVote")).map(BooleanResponse.apply)


  def privGetTransactionCount(address: String, privacyGroupId: Base64String): F[EthGetTransactionCount] =
    services.fetch[String](Request(method = "priv_getTransactionCount")).map(EthGetTransactionCount.apply)

  def privGetPrivateTransaction(transactionHash: String): F[PrivGetPrivateTransaction] =
    services.fetch[String](Request(method = "priv_getPrivateTransaction")).map(PrivGetPrivateTransaction.apply)


  def privDistributeRawTransaction(signedTransactionData: String): F[PrivateEnclaveKey]=
    services.fetch[String](Request(method = "priv_distributeRawTransaction")).map(PrivateEnclaveKey.apply)


  def privGetPrivacyPrecompileAddress: F[PrivGetPrivacyPrecompileAddress]=
    services.fetch[String](Request(method = "priv_getPrivacyPrecompileAddress")).map(PrivGetPrivacyPrecompileAddress.apply)


  def privCreatePrivacyGroup(addresses: List[Base64String], name: String, description: String): F[PrivCreatePrivacyGroup]=
    services.fetch[String](Request(method = "priv_createPrivacyGroup")).map(PrivCreatePrivacyGroup.apply)


//  def privOnChainSetGroupLockState(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, lock: Boolean): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//  def privOnChainAddToPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//
//  def privOnChainCreatePrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//
//  def privOnChainRemoveFromPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participant: Base64String): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//

  def privOnChainFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]=
    services.fetch[String](Request(method = "privx_findOnChainPrivacyGroup")).map(PrivFindPrivacyGroup.apply)


  def privFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]=
    services.fetch[String](Request(method = "priv_findPrivacyGroup")).map(PrivFindPrivacyGroup.apply)


  def privDeletePrivacyGroup(privacyGroupId: Base64String): F[BooleanResponse]=
    services.fetch[String](Request(method = "priv_deletePrivacyGroup")).map(BooleanResponse.apply)


  def privGetTransactionReceipt(transactionHash: String): F[PrivGetTransactionReceipt]=
    services.fetch[String](Request(method = "priv_getTransactionReceipt")).map(PrivGetTransactionReceipt.apply)


  def privGetCode(privacyGroupId: String, address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode]=
    services.fetch[String](Request(method = "priv_getCode")).map(EthGetCode.apply)


  def privCall(privacyGroupId: String, transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall]=
    services.fetch[String](Request(method = "priv_call")).map(EthCall.apply)


  def privGetLogs(privacyGroupId: String, ethFilter: EthFilter): F[EthLog]=
    services.fetch[String](Request(method = "priv_getLogs")).map(EthLog.apply)


  def privNewFilter(privacyGroupId: String, ethFilter: EthFilter): F[EthFilter]=
    services.fetch[String](Request(method = "priv_newFilter")).map(EthFilter.apply)


  def privUninstallFilter(privacyGroupId: String, filterId: String): F[EthUninstallFilter]=
    services.fetch[String](Request(method = "priv_uninstallFilter")).map(EthUninstallFilter.apply)


  def privGetFilterChanges(privacyGroupId: String, filterId: String): F[EthLog]=
    services.fetch[String](Request(method = "priv_getFilterChanges")).map(EthLog.apply)


  def privGetFilterLogs(privacyGroupId: String, filterId: String): F[EthLog] =
    services.fetch[String](Request(method = "priv_getFilterLogs")).map(EthLog.apply)



