package org.web3s.contracts.token

import org.web3s.protocol.core.methods.response.model.TransactionReceipt
trait ERC20BasicInterface[F[_],T]:
  def totalSupply: F[BigInt]
  def balanceOf(who: String): F[BigInt]
  def transfer(to: String, value: BigInt): F[TransactionReceipt]
  def transferEvents(transactionReceipt: TransactionReceipt): F[List[T]]

trait ERC20Interface[F[_],R, T] extends ERC20BasicInterface[F,T]:
  def allowance(owner: String, spender: String):  F[BigInt]
  def approve(spender: String, value: BigInt): F[TransactionReceipt]
  def transferFrom(from: String, to: String, value: BigInt): F[TransactionReceipt]
  def approvalEvents(transactionReceipt: TransactionReceipt):  F[List[R]]
