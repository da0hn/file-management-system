package dev.da0hn.file.management.system.data.db.repositories

import dev.da0hn.file.management.system.data.db.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
  @Query("SELECT u FROM User u WHERE u.username = :username")
  fun findByUsername(username: String): UserEntity?


}
