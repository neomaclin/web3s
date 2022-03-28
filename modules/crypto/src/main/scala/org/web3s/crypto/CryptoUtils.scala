/*
 * Copyright 2022 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *//*
 * Copyright 2022 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3s.crypto

import org.bouncycastle.asn1.{ASN1InputStream, ASN1Integer, DERSequenceGenerator, DLSequence}

import java.io.{ByteArrayOutputStream, IOException}
import scala.util.{Try, Using}

object CryptoUtils:

  def toDerFormat(signature: ECDSASignature): Array[Byte] =
   val result = Using.resource(new ByteArrayOutputStream()) { baos =>
      Try(new DERSequenceGenerator(baos)).flatMap { seq =>
        seq.addObject(new ASN1Integer(signature.r.bigInteger))
        seq.addObject(new ASN1Integer(signature.s.bigInteger))
        Try(seq.close())
      }
      baos.toByteArray
    }
   result
  end toDerFormat

  def fromDerFormat(bytes: Array[Byte]): ECDSASignature =
    Using.resource(new ASN1InputStream(bytes)) { decoder =>
      val seq = decoder.readObject.asInstanceOf[DLSequence]
      if (seq == null) throw new RuntimeException("Reached past end of ASN.1 stream.")
      
      val result = for
        r <- Try(seq.getObjectAt(0).asInstanceOf[ASN1Integer])
        s <- Try(seq.getObjectAt(1).asInstanceOf[ASN1Integer])
      yield
        ECDSASignature(r.getPositiveValue(), s.getPositiveValue())

      result.get
    }
  end fromDerFormat


end CryptoUtils
