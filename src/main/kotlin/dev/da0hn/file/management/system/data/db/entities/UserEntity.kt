package dev.da0hn.file.management.system.data.db.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.io.Serial
import java.io.Serializable
import java.util.UUID

@Entity(name = "User")
@Table(name = "user")
class UserEntity(
  @Id
  val id: UUID?,
  @Column(name = "username", nullable = false, length = 255, unique = true)
  val username: String,
  @Column(name = "password", nullable = false)
  val password: String,
  @Column(name = "name", nullable = false, length = 255)
  val name: String,
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "role_id", nullable = false, updatable = false, insertable = false)
  val role: RoleEntity,
) : Serializable {
  companion object {
    @Serial
    private const val serialVersionUID: Long = -5234679263129163785L
  }

}
