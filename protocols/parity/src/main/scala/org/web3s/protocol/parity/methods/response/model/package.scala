package org.web3s.protocol.parity.methods.response

package model:

  final case class VMTrace(code: String, ops: List[VMOperation])

  final case class Mem(data: String, off: BigInt)

  final case class Store(key: String, `val`: String)

  final case class Ex(mem: Mem, push: List[String], store: Store, used: BigInt)

  final case class VMOperation(sub: VMTrace, cost: BigInt, ex: Ex, pc: BigInt)