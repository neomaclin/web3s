//package org.web3s.abi.datatypes
//
//import org.web3s.abi.codec.Encodable
//import org.web3s.abi.datatypes.*
//import org.web3s.abi.datatypes.generated.*
//
//final case class Bar(id: BigInt, data: BigInt) extends StaticStruct[UInt256](new UInt256(id),new UInt256(data))
//
//object Bar:
//  given Encodable[Bar] = new Encodable[Bar]:
//    override def encode(value: Bar): String = StaticStruct.encode(value)
//    override def encodePacked(value: Bar): String = StaticStruct.encode(value)
//
