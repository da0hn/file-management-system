package dev.da0hn.file.management.system.application.configuration.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class UnauthorizedHandler : AuthenticationEntryPoint {

  val logger = KotlinLogging.logger {  }

  override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
    logger.info { "Unauthorized error: ${authException?.message ?: "Unknown Error"}" }
    response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
  }
}
