package dev.da0hn.file.management.system.application.configuration.security.adapter

import dev.da0hn.file.management.system.application.configuration.security.TokenType
import dev.da0hn.file.management.system.core.annotation.Adapter
import dev.da0hn.file.management.system.core.ports.spi.AuthenticationService
import dev.da0hn.file.management.system.core.ports.spi.AuthenticationService.AuthenticatedUserDetails
import dev.da0hn.file.management.system.core.ports.spi.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

@Adapter
class AuthenticationServiceAdapter(
  private val authenticationManager: AuthenticationManager,
  private val jwtService: JwtService,
) : AuthenticationService {
  override fun authenticate(username: String, password: String): AuthenticatedUserDetails {
    val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    val userDetails = authentication.principal as UserDetails
    val jwtToken = jwtService.generateToken(userDetails.username, TokenType.AUTH_TOKEN)
    val refreshToken = jwtService.generateToken(userDetails.username, TokenType.REFRESH_TOKEN)
    return AuthenticatedUserDetails(
      userDetails.username,
      jwtToken,
      refreshToken
    )
  }
}
