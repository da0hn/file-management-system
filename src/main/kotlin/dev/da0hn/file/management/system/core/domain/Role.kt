package dev.da0hn.file.management.system.core.domain

import java.util.UUID

class Role(
  roleId: RoleId,
  val name: String,
) : Entity<RoleId>(roleId) {
  override fun toString(): String {
    return "Role{id='$id', name='$name'}"
  }
}

class RoleId private constructor(value: String) : EntityId(value) {
  companion object : EntityIdFactory<RoleId> {
    override fun fromString(value: String): RoleId {
      return RoleId(value)
    }

    override fun newInstance(): RoleId {
      return RoleId(UUID.randomUUID().toString())
    }

  }
}


