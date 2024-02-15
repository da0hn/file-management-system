package dev.da0hn.file.management.system.core.exceptions

import java.io.Serial

class DataNotFoundException : RuntimeException {
  constructor() : super()
  constructor(message: String?) : super(message)
  constructor(message: String?, cause: Throwable?) : super(message, cause)

  companion object {
    @Serial
    private const val serialVersionUID: Long = -8922722128671130573L
  }
}
