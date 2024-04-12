package dev.da0hn.file.management.system.core.usecases

import dev.da0hn.file.management.system.core.annotation.UseCase
import dev.da0hn.file.management.system.core.domain.CreateNewUserCommand
import dev.da0hn.file.management.system.core.domain.TextEncoder
import dev.da0hn.file.management.system.core.domain.User
import dev.da0hn.file.management.system.core.ports.api.CreateUserUseCase
import dev.da0hn.file.management.system.core.ports.spi.UserRepository

@UseCase
class CreateUserUseCaseImpl(
  private val userRepository: UserRepository,
  private val textEncoder: TextEncoder
) : CreateUserUseCase {

  override fun execute(input: CreateUserUseCase.Input): CreateUserUseCase.Output {

    val newUser = User.newUser(
      CreateNewUserCommand(
        name = input.name,
        username = input.username,
        password = input.password,
        passwordConfirmation = input.passwordConfirmation,
        role = input.role
      ),
      this.textEncoder
    )

    this.userRepository.save(newUser)

    return CreateUserUseCase.Output(newUser.id.value)
  }
}
