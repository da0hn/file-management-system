package dev.da0hn.file.management.system.data.db.mappers

import dev.da0hn.file.management.system.core.annotation.Mapper
import dev.da0hn.file.management.system.core.domain.Role
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.core.domain.UserId
import dev.da0hn.file.management.system.data.db.entities.UserEntity

@Mapper
class ToUserDomainConverter : EntityToDomainConverter<UserEntity, User> {

  override fun toDomain(source: UserEntity): User {
    return User(
      id = source.id?.let { UserId.fromString(it) }!!,
      username = source.username,
      password = source.password,
      name = source.name,
      role = Role.valueOf(source.role.name)
    )
  }

}
