package dev.da0hn.file.management.system.core.ports.api

interface UserAuthenticationUseCase {

  fun execute(input: Input): Output

  data class Input(
    val username: String,
    val password: String,
  )

  data class Output(
    val userId: String,
    val username: String,
    val name: String,
    val jwtToken: String,
    val refreshToken: String,
    val role: String,
  )

}
