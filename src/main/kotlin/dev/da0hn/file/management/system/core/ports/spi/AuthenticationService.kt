package dev.da0hn.file.management.system.core.ports.spi

interface AuthenticationService {

  fun authenticate(username: String, password: String): AuthenticatedUserDetails


  data class AuthenticatedUserDetails(val username: String, val jwtToken: String, val refreshToken: String)
}

