package dev.da0hn.file.management.system.data.db.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serial
import java.io.Serializable

@Entity(name = "User")
@Table(name = "user")
class UserEntity(
  @Id
  val id: String?,
  @Column(name = "username", nullable = false, length = 255, unique = true)
  val username: String,
  @Column(name = "password", nullable = false)
  val password: String,
  @Column(name = "name", nullable = false, length = 255)
  val name: String,
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  val role: RoleEntityEnum,
) : Serializable {
  companion object {
    @Serial
    private const val serialVersionUID: Long = -5234679263129163785L
  }

}
