package org.web3s.abi.datatypes


abstract class NumericType(val `type`: String, override val value: BigInt) extends AbiType[BigInt] :
  def getTypeAsString: String = `type`

  def bitSize: Int

  override def equals(o: Any): Boolean = o match 
    case other: NumericType => other.value == value && other.`type` == `type`
    case _ => false
  
  override def hashCode: Int = 31 * `type`.hashCode + (if (value != null) value.hashCode else 0)

end NumericType
