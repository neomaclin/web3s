package org.web3s.codegen

import java.util.regex.Pattern

object SolidityFunctionWrapper:
  private val BINARY = "BINARY"
  private val WEB3J = "web3j"
  private val CREDENTIALS = "credentials"
  private val CONTRACT_GAS_PROVIDER = "contractGasProvider"
  private val TRANSACTION_MANAGER = "transactionManager"
  private val INITIAL_VALUE = "initialWeiValue"
  private val CONTRACT_ADDRESS = "contractAddress"
  private val GAS_PRICE = "gasPrice"
  private val GAS_LIMIT = "gasLimit"
  private val FILTER = "filter"
  private val START_BLOCK = "startBlock"
  private val END_BLOCK = "endBlock"
  private val WEI_VALUE = "weiValue"
  private val FUNC_NAME_PREFIX = "FUNC_"
  private val TYPE_FUNCTION = "function"
  private val TYPE_EVENT = "event"
  private val TYPE_CONSTRUCTOR = "constructor"

  //private val LOG = ClassName.get(classOf[Log])
  //private val LOGGER = LoggerFactory.getLogger(classOf[SolidityFunctionWrapper])
  private val ARRAY_SUFFIX = "\\[(\\d*)]".r

  private val regex = "(\\w+)(?:\\[(.*?)\\])(?:\\[(.*?)\\])?".r

  //private val CODEGEN_WARNING = "<p>Auto generated code.\n<p><strong>Do not modify!</strong>\n<p>Please use the <a href=\"https://docs.web3j.io/command_line.html\">web3j command line tools</a>,\nor the " + classOf[SolidityFunctionWrapperGenerator].getName + " in the \n<a href=\"https://github.com/web3j/web3j/tree/master/codegen\">codegen module</a> to update.\n"


