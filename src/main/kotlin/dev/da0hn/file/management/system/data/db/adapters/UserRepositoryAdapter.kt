package dev.da0hn.file.management.system.data.db.adapters

import dev.da0hn.file.management.system.core.annotation.Adapter
import dev.da0hn.file.management.system.core.domain.Role
import dev.da0hn.file.management.system.core.domain.RoleId
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.core.domain.UserId
import dev.da0hn.file.management.system.core.ports.spi.UserRepository
import dev.da0hn.file.management.system.data.db.repositories.UserJpaRepository

@Adapter
class UserRepositoryAdapter(
  private val repository: UserJpaRepository,
) : UserRepository {
  override fun findByUsername(username: String): User? {
    return this.repository.findByUsername(username)?.let {
      User(UserId.of(it.id!!), it.username, it.password, it.name, Role(RoleId.of(it.role.id!!), it.role.name))
    }
  }
}
