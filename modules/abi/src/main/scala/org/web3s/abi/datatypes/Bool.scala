package org.web3s.abi.datatypes

/** Boolean type. */
object Bool {
  val TYPE_NAME = "bool"
  val DEFAULT = new Bool(false)
}

class Bool(override val value: Boolean) extends SolidityType[Boolean]:
  
  override def getTypeAsString: String = Bool.TYPE_NAME

  override def equals(o: Any): Boolean = o match 
    case other: Bool =>  value == other.value
    case _ => false
  
  override def hashCode: Int = if (value) 1 else 0
  
end Bool
