package org.web3s.protocol

enum Network(val network: String):
  case MAINNET extends Network("mainnet")
  case ROPSTEN  extends Network("ropsten")
  case KOVAN extends Network("kovan")
  case GORLI extends Network("gorli")
  case RINKEBY extends Network("rinkeby")
