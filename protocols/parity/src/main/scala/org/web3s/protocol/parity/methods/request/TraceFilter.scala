package org.web3s.protocol.parity.methods.request

import org.web3s.protocol.core.DefaultBlockParameter

final case class TraceFilter(fromBlock: DefaultBlockParameter,
                             toBlock: DefaultBlockParameter,
                             fromAddress: List[String],
                             toAddress: List[String])