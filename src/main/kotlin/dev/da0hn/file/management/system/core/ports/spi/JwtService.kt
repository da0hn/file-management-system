package dev.da0hn.file.management.system.core.ports.spi

import dev.da0hn.file.management.system.application.configuration.security.TokenType
import org.springframework.security.core.userdetails.UserDetails

interface JwtService {
  fun extractUsername(token: String, type: TokenType): String?
  fun validateToken(token: String, type: TokenType, userDetails: UserDetails): Boolean
  fun generateToken(userName: String, type: TokenType): String
}
