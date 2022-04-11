
package org.web3s.codegen

import cats.effect.Concurrent
import fs2.{Stream, hash, text}
import fs2.io.file.{Files, Path}

trait Generator:

  def write[F[_] : Files : Concurrent](files: Stream[F, (String, String)], destinationDir: String): F[Unit] =

    def fileStream(content: String, fileName: String): Stream[F, Path] =
      Stream(content).through(text.utf8.encode).through(Files[F].writeAll(Path(destinationDir + fileName)))
    end fileStream

    files.flatMap(fileStream).compile.drain

  end write


end Generator