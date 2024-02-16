package dev.da0hn.file.management.system.core.usecases

import dev.da0hn.file.management.system.core.annotation.UseCase
import dev.da0hn.file.management.system.core.extensions.orThrowDataNotFound
import dev.da0hn.file.management.system.core.ports.api.UserAuthenticationUseCase
import dev.da0hn.file.management.system.core.ports.spi.AuthenticationService
import dev.da0hn.file.management.system.core.ports.spi.UserRepository

@UseCase
class UserAuthenticationUseCaseImpl(
  private val userRepository: UserRepository,
  private val authenticationService: AuthenticationService,
) : UserAuthenticationUseCase {
  override fun execute(input: UserAuthenticationUseCase.Input): UserAuthenticationUseCase.Output {
    val user = userRepository.findByUsername(input.username).orThrowDataNotFound { "User not found" }
    val authentication = this.authenticationService.authenticate(user.username, input.password)
    return UserAuthenticationUseCase.Output(
      user.id.value,
      authentication.username,
      user.name,
      authentication.jwtToken,
      authentication.refreshToken,
      user.role.name
    )
  }

}
