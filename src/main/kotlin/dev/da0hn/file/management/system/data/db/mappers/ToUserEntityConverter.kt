package dev.da0hn.file.management.system.data.db.mappers

import dev.da0hn.file.management.system.core.annotation.Mapper
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.data.db.entities.RoleEntityEnum
import dev.da0hn.file.management.system.data.db.entities.UserEntity

@Mapper
class ToUserEntityConverter : DomainToEntityConverter<User, UserEntity> {

  override fun toEntity(source: User): UserEntity {
    return UserEntity(
      id = source.id.value,
      username = source.username,
      password = source.password,
      name = source.name,
      role = RoleEntityEnum.valueOf(source.role.name)
    )
  }

}
