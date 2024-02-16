package dev.da0hn.file.management.system.core.domain

import java.util.UUID

class User(
  id: UserId,
  val username: String,
  val password: String,
  val name: String,
  val role: Role,
) : Entity<UserId>(id) {

  override fun toString(): String {
    return "User{id='$id', username='$username', password='$password', name='$name', role=$role}"
  }

}

class UserId private constructor(value: String) : EntityId(value) {
  companion object : EntityIdFactory<UserId> {
    override fun of(value: UUID): UserId {
      return UserId(value.toString())
    }

    override fun fromString(value: String): UserId {
      return UserId(value)
    }

    override fun newInstance(): UserId {
      return UserId(UUID.randomUUID().toString())
    }
  }
}
