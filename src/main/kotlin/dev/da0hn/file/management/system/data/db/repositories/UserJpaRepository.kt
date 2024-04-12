package dev.da0hn.file.management.system.data.db.repositories

import dev.da0hn.file.management.system.data.db.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, String> {
  @Query("SELECT u FROM User u WHERE u.username = :username")
  fun findByUsername(username: String): UserEntity?


}
