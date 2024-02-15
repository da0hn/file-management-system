package dev.da0hn.file.management.system.data.db.repositories

import dev.da0hn.file.management.system.data.db.entities.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoleJpaRepository : JpaRepository<RoleEntity, UUID>
