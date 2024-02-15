package dev.da0hn.file.management.system.core.extensions

import dev.da0hn.file.management.system.core.exceptions.DataNotFoundException


inline fun <T> T?.orThrow(throwable: () -> Throwable): T {
  if (this == null) {
    throw throwable()
  }
  return this
}

inline fun <T> T?.orThrowDataNotFound(message: () -> String): T {
  if (this == null) {
    throw DataNotFoundException(message())
  }
  return this
}

