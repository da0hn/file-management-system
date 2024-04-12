package dev.da0hn.file.management.system.core.ports.api

import dev.da0hn.file.management.system.core.domain.Role

interface CreateUserUseCase {

  fun execute(input: Input): Output

  data class Input(
    val name: String,
    val username: String,
    val password: String,
    val passwordConfirmation: String,
    val role: Role,
  )

  data class Output(val userId: String)

}
