package dev.da0hn.file.management.system.application.configuration.security

import dev.da0hn.file.management.system.application.configuration.properties.JwtProperties
import dev.da0hn.file.management.system.core.ports.spi.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JwtServiceAdapter(private val jwtProperties: JwtProperties) : JwtService {

  private val logger = KotlinLogging.logger { }

  override fun extractUsername(token: String, type: TokenType): String? {
    return this.extractClaim(token, Claims::getSubject, type)
  }

  override fun validateToken(token: String, type: TokenType, userDetails: UserDetails): Boolean {
    try {
      Jwts.parserBuilder()
        .setSigningKey(this.getSignKey(type))
        .build()
        .parse(token)
      val username = this.extractUsername(token, type)
      val tokenExpired = this.isTokenExpired(token, type)
      return (username == userDetails.username && tokenExpired != null && !tokenExpired)
    } catch (e: MalformedJwtException) {
      logger.error { "Invalid JWT token: ${e.message}" }
    } catch (e: ExpiredJwtException) {
      logger.error { "JWT token is expired: ${e.message}" }
    } catch (e: UnsupportedJwtException) {
      logger.error { "JWT token is unsupported: ${e.message}" }
    } catch (e: IllegalArgumentException) {
      logger.error { "JWT claims string is empty: ${e.message}" }
    }
    return false
  }

  override fun generateToken(userName: String, type: TokenType): String {
    val claims: Map<String, Any> = mapOf()
    return this.createToken(claims, userName, type)
  }

  private fun extractExpiration(token: String, type: TokenType): Date? {
    return this.extractClaim(token, Claims::getExpiration, type)
  }

  private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T?, type: TokenType): T? {
    val claims: Claims = this.extractAllClaims(token, type)
    return claimsResolver(claims)
  }

  private fun extractAllClaims(token: String, type: TokenType): Claims {
    return Jwts
      .parserBuilder()
      .setSigningKey(this.getSignKey(type))
      .build()
      .parseClaimsJws(token)
      .body
  }

  private fun isTokenExpired(token: String, type: TokenType): Boolean? {
    return this.extractExpiration(token, type)?.before(Date())
  }

  private fun createToken(claims: Map<String, Any>, userName: String, type: TokenType): String {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(userName)
      .setIssuedAt(Date(System.currentTimeMillis()))
      .setExpiration(this.getExpiration(type))
      .signWith(this.getSignKey(type), SignatureAlgorithm.HS256).compact()
  }

  private fun getExpiration(type: TokenType): Date {
    return when (type) {
      TokenType.AUTH_TOKEN -> Date(System.currentTimeMillis() + this.jwtProperties.expiration.toMillis())
      TokenType.REFRESH_TOKEN -> Date(System.currentTimeMillis() + this.jwtProperties.refreshExpiration.toMillis())
    }
  }

  private fun getSignKey(type: TokenType): Key {
    val secret = when (type) {
      TokenType.AUTH_TOKEN -> jwtProperties.secret
      TokenType.REFRESH_TOKEN -> jwtProperties.refreshSecret
    }
    val keyBytes = Decoders.BASE64.decode(secret)
    return Keys.hmacShaKeyFor(keyBytes)
  }


}

