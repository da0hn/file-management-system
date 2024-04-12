package dev.da0hn.file.management.system.data.db.adapters

import dev.da0hn.file.management.system.core.annotation.Adapter
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.core.extensions.orThrow
import dev.da0hn.file.management.system.core.ports.spi.UserRepository
import dev.da0hn.file.management.system.data.db.entities.UserEntity
import dev.da0hn.file.management.system.data.db.repositories.UserJpaRepository
import org.springframework.core.convert.ConversionService

@Adapter
class UserRepositoryAdapter(
  private val repository: UserJpaRepository,
  private val conversionService: ConversionService,
) : UserRepository {
  override fun findByUsername(username: String): User? {
    return this.repository.findByUsername(username)?.let {
      this.conversionService.convert(it, User::class.java)
    }
  }

  override fun save(user: User): User {
    val userEntity = this.conversionService.convert(user, UserEntity::class.java)
      .orThrow { IllegalArgumentException("Cannot convert User to UserEntity") }
    return this.conversionService.convert(
      this.repository.save(userEntity), User::class.java
    ).orThrow { IllegalArgumentException("Cannot convert UserEntity to User") }
  }

  override fun findById(userId: String): User? {
    return this.repository.findById(userId).let {
      this.conversionService.convert(it, User::class.java)
    }
  }
}
