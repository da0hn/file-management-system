package dev.da0hn.file.management.system.core.domain

import java.util.UUID

abstract class Entity<ID : EntityId>(val id: ID) {
  override fun toString(): String {
    return "{id='${id.value}'}"
  }

}

sealed class EntityId(val value: String) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is EntityId) return false

    if (value != other.value) return false

    return true
  }

  override fun hashCode(): Int {
    return value.hashCode()
  }

  override fun toString(): String {
    return "EntityId{value='$value'}"
  }
}


interface EntityIdFactory<T : EntityId> {
  fun of(value: UUID): T
  fun fromString(value: String): T
  fun newInstance(): T
}
