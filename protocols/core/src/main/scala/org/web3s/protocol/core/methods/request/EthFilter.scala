package org.web3s.protocol.core.methods.request

import org.web3s.protocol.core.DefaultBlockParameter

final case class EthFilter(fromBlock: Option[DefaultBlockParameter] = None,
                           toBlock: Option[DefaultBlockParameter] = None,
                           blockHash: Option[String] = None,
                           address: Option[List[String]] = None,
                           override val topics: List[FilterTopic[_]] = Nil
                          ) extends Filter[EthFilter] :
  override def filter: EthFilter = this


object EthFilter:
  def from(fromBlock: DefaultBlockParameter, toBlock: DefaultBlockParameter, address: List[String]): EthFilter =
    EthFilter(Some(fromBlock), Some(toBlock), None, Some(address))

  def from(fromBlock: DefaultBlockParameter, toBlock: DefaultBlockParameter, address: String): EthFilter =
    EthFilter(Some(fromBlock), Some(toBlock), None, Some(List(address)))

  def from(blockHash: String, address: String): EthFilter =
    EthFilter(None, None, Some(blockHash), Some(List(address)))

  def from(blockHash: String): EthFilter =
    EthFilter(None, None, Some(blockHash), None)
