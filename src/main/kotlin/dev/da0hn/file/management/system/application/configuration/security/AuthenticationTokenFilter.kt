package dev.da0hn.file.management.system.application.configuration.security

import dev.da0hn.file.management.system.core.ports.spi.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter


class AuthenticationTokenFilter(
  private val userDetailsService: UserDetailsService,
  private val jwtService: JwtService,
) : OncePerRequestFilter() {


  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    try {
      val jwt = this.parseJwt(request)
      val userDetails = this.userDetailsService.loadUserByUsername(this.jwtService.extractUsername(jwt, TokenType.AUTH_TOKEN))
      val username = this.jwtService.extractUsername(jwt, TokenType.AUTH_TOKEN)
      if (username != null && SecurityContextHolder.getContext().authentication == null) {
        if (this.jwtService.validateToken(jwt, TokenType.AUTH_TOKEN, userDetails)) {
          val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
          authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
          SecurityContextHolder.getContext().authentication = authentication
        }
      }
    } catch (e: Exception) {
      logger.error { "Cannot set user authentication: ${e.message}" }
    }
    filterChain.doFilter(request, response)
  }

  private fun parseJwt(request: HttpServletRequest): String {
    val headerAuth = request.getHeader("Authorization")

    if (!StringUtils.hasText(headerAuth) || !headerAuth.startsWith("Bearer ")) {
      throw IllegalArgumentException("Invalid JWT token")
    }
    return headerAuth.substring(7)
  }
}
