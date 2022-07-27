package org.web3s.protocol.besu.methods.response.model

import org.web3s.protocol.core.methods.response.model.Log

final case class PrivateTransactionReceipt(contractAddress: String,
                                           from: String,
                                           to: String,
                                           output: String,
                                           logs: List[Log],
                                           commitmentHash: String,
                                           transactionHash: String,
                                           privateFrom: String,
                                           privateFor: List[String],
                                           privacyGroupId: String,
                                           status: String,
                                           revertReason: String)
