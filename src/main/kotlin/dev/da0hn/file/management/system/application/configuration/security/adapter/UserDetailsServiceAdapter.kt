package dev.da0hn.file.management.system.application.configuration.security.adapter

import dev.da0hn.file.management.system.core.annotation.Adapter
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.core.extensions.orThrowDataNotFound
import dev.da0hn.file.management.system.core.ports.spi.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.io.Serial

@Adapter
class UserDetailsServiceAdapter(
  private val userRepository: UserRepository,
) : UserDetailsService {
  override fun loadUserByUsername(maybeUsername: String?): UserDetails {
    return maybeUsername.orThrowDataNotFound { "Username is required" }
      .let(userRepository::findByUsername)
      .orThrowDataNotFound { "User not found" }
      .let(::UserDetailsImpl)
  }

  class UserDetailsImpl(private val entity: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
      return listOf(entity.role).map { r -> GrantedAuthority { r.name } }.toMutableList()
    }

    override fun getPassword(): String = entity.password

    override fun getUsername(): String = entity.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
      @Serial
      private const val serialVersionUID: Long = 6785606890833345468L
    }

  }

}
