package dev.da0hn.file.management.system.data.db.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serial
import java.io.Serializable
import java.util.UUID

@Entity(name = "Role")
@Table(name = "role")
class RoleEntity(
  @Id
  val id: UUID?,
  @Column(name = "name", nullable = false, length = 255, unique = true)
  val name: String,
) : Serializable {
  companion object {
    @Serial
    private const val serialVersionUID: Long = -8680620977056531462L
  }
}
