package org.web3s

import cats.MonadThrow
import cats.syntax.functor.*
import org.web3s.utils.EthBigInt
import org.web3s.protocol.core.Request
import org.web3s.crypto.Credentials
import org.web3s.protocol.core.*
import org.web3s.protocol.besu.Besu
import org.web3s.protocol.besu.methods.request.CreatePrivacyGroupRequest
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.besu.methods.response.*
import org.web3s.protocol.besu.methods.response.model.*
import org.web3s.protocol.besu.methods.response.privacy.{PrivFindPrivacyGroup, PrivGetPrivateTransaction, *}
import org.web3s.protocol.core.methods.request
import org.web3s.protocol.eea.util.Base64String

import org.web3s.services.Web3sService

class Web3sBesu[F[_] : MonadThrow](services: Web3sService[F]) extends Besu[F] :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.core.methods.request.encoder.given
  import org.web3s.protocol.core.methods.response.decoders.given

  import org.web3s.protocol.eea.util.Base64String.given
  def minerStart: F[MinerStartResponse] =
    services.fetch[Unit](Request(method = "miner_start")).map(MinerStartResponse.apply)

  def minerStop: F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "miner_stop")).map(BooleanResponse.apply)


  def cliqueDiscard(addressStr: String): F[BooleanResponse] =
    val request = Request(method = "clique_discard", params = List(addressStr.asJson))
    services.fetch[Boolean](request).map(BooleanResponse.apply)

  def cliqueGetSigners(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts] =
    val request = Request(method = "clique_getSigners", params = List(defaultBlockParameter.asJson))
    services.fetch[Seq[String]](request).map(EthAccounts.apply)

  def cliqueGetSignersAtHash(blockHash: String): F[EthAccounts] =
    val params = List(blockHash.asJson)
    val request = Request(method = "clique_getSignersAtHash", params)
    services.fetch[Seq[String]](request).map(EthAccounts.apply)

  def cliquePropose(address: String, signerAddition: Boolean): F[BooleanResponse] =
    val params = List(address.asJson, signerAddition.asJson)
    val request = Request(method = "clique_propose", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)

  def cliqueProposals: F[BesuEthAccountsMapResponse] =
    val request = Request(method = "clique_proposals")
    services.fetch[Map[String, Boolean]](request).map(BesuEthAccountsMapResponse.apply)

  def debugTraceTransaction(transactionHash: String, options: Map[String, Boolean]): F[BesuFullDebugTraceResponse] =
    val params = List(transactionHash.asJson, options.asJson)
    val request = Request(method = "debug_traceTransaction", params)
    services.fetch[BesuFullDebugTraceResponse.FullDebugTraceInfo](request).map(BesuFullDebugTraceResponse.apply)

  def ibftDiscardValidatorVote(address: String): F[BooleanResponse] =
    val params = List(address.asJson)
    val request = Request(method = "ibft_discardValidatorVote", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def ibftGetPendingVotes: F[BesuEthAccountsMapResponse] =
    services.fetch[Map[String, Boolean]](Request(method = "ibft_getPendingVotes")).map(BesuEthAccountsMapResponse.apply)

  def ibftGetSignerMetrics: F[BesuSignerMetrics] =
    services.fetch[List[BesuSignerMetrics.BesuSignerMetric]](Request(method = "ibft_getSignerMetrics")).map(BesuSignerMetrics.apply)


  def ibftGetValidatorsByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts] =
    val request = Request(method = "ibft_getValidatorsByBlockNumber", params = List(defaultBlockParameter.asJson))
    services.fetch[Seq[String]](request).map(EthAccounts.apply)


  def ibftGetValidatorsByBlockHash(blockHash: String): F[EthAccounts] =
    val request = Request(method = "ibft_getValidatorsByBlockHash", params = List(blockHash.asJson))
    services.fetch[Seq[String]](request).map(EthAccounts.apply)


  def ibftProposeValidatorVote(address: String, validatorAddition: Boolean): F[BooleanResponse] =
    val params = List(address.asJson, validatorAddition.asJson)
    val request = Request(method = "ibft_proposeValidatorVote", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def privGetTransactionCount(address: String, privacyGroupId: Base64String): F[EthGetTransactionCount] =
    val params = List(address.asJson, privacyGroupId.asJson)
    val request = Request(method = "priv_getTransactionCount", params)
    services.fetch[EthBigInt](request).map(EthGetTransactionCount.apply)

  def privGetPrivateTransaction(transactionHash: String): F[PrivGetPrivateTransaction] =
    val request = Request(method = "priv_getPrivateTransaction", params = List(transactionHash.asJson))
    services.fetch[PrivGetPrivateTransaction.PrivateTransaction](request).map(PrivGetPrivateTransaction.apply)


  def privDistributeRawTransaction(signedTransactionData: String): F[PrivateEnclaveKey] =
    val request = Request(method = "priv_distributeRawTransaction", params = List(signedTransactionData.asJson))
    services.fetch[String](request).map(PrivateEnclaveKey.apply)


  def privGetPrivacyPrecompileAddress: F[PrivGetPrivacyPrecompileAddress] =
    services.fetch[String](Request(method = "priv_getPrivacyPrecompileAddress")).map(PrivGetPrivacyPrecompileAddress.apply)


  def privCreatePrivacyGroup(addresses: List[Base64String], name: String, description: String): F[PrivCreatePrivacyGroup] =
    val params = List(CreatePrivacyGroupRequest(addresses, name, description).asJson)
    val request = Request(method = "priv_createPrivacyGroup", params)
    services.fetch[Base64String](request).map(PrivCreatePrivacyGroup.apply)


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

  def privOnChainFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup] =
    val params = List(addresses.asJson)
    val request = Request(method = "privx_findOnChainPrivacyGroup",params)
    services.fetch[List[PrivFindPrivacyGroup.PrivacyGroup]](request).map(PrivFindPrivacyGroup.apply)


  def privFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup] =
    val params = List(addresses.asJson)
    val request = Request(method = "priv_findPrivacyGroup", params)
    services.fetch[List[PrivFindPrivacyGroup.PrivacyGroup]](request).map(PrivFindPrivacyGroup.apply)


  def privDeletePrivacyGroup(privacyGroupId: Base64String): F[BooleanResponse] =
    val params = List(privacyGroupId.asJson)
    val request = Request(method = "priv_deletePrivacyGroup", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def privGetTransactionReceipt(transactionHash: String): F[PrivGetTransactionReceipt] =
    val params = List(transactionHash.asJson)
    val request = Request(method = "priv_getTransactionReceipt", params)
    services.fetch[PrivateTransactionReceipt](request).map(PrivGetTransactionReceipt.apply)


  def privGetCode(privacyGroupId: String, address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode] =
    val params = List(privacyGroupId.asJson, address.asJson, defaultBlockParameter.asJson)
    val request = Request(method = "priv_getCode", params)
    services.fetch[String](request).map(EthGetCode.apply)


  def privCall(privacyGroupId: String, transaction: request.Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall] =
    val params = List(privacyGroupId.asJson, transaction.asJson.dropNullValues, defaultBlockParameter.asJson )
    val request = Request(method = "priv_call", params)
    services.fetch[String](request).map(EthCall.apply)


  def privGetLogs(privacyGroupId: String, ethFilter: request.EthFilter): F[EthLog] =
    val params = List(privacyGroupId.asJson, ethFilter.asJson.dropNullValues)
    val request = Request(method = "priv_getLogs", params)
    services.fetch[List[EthLog.LogResult]](request).map(EthLog.apply)


  def privNewFilter(privacyGroupId: String, ethFilter: request.EthFilter): F[EthFilter] =
    val params = List(privacyGroupId.asJson, ethFilter.asJson.dropNullValues)
    val request = Request(method = "priv_newFilter", params)
    services.fetch[EthBigInt](request).map(EthFilter.apply)


  def privUninstallFilter(privacyGroupId: String, filterId: String): F[EthUninstallFilter] =
    val params = List(privacyGroupId.asJson, filterId.asJson)
    val request = Request(method = "priv_uninstallFilter", params)
    services.fetch[Boolean](request).map(EthUninstallFilter.apply)


  def privGetFilterChanges(privacyGroupId: String, filterId: String): F[EthLog] =
    val params = List(privacyGroupId.asJson, filterId.asJson)
    val request = Request(method = "priv_getFilterChanges", params)
    services.fetch[List[EthLog.LogResult]](request).map(EthLog.apply)

  def privGetFilterLogs(privacyGroupId: String, filterId: String): F[EthLog] =
    val params = List(privacyGroupId.asJson, filterId.asJson)
    val request = Request(method = "priv_getFilterLogs", params)
    services.fetch[List[EthLog.LogResult]](request).map(EthLog.apply)



