package dev.da0hn.file.management.system.application.controller

import dev.da0hn.file.management.system.application.controller.dto.CreateUserRequest
import dev.da0hn.file.management.system.core.ports.api.CreateUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
  private val createUserUseCase: CreateUserUseCase,
) {

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<CreateUserUseCase.Output> {
    val output = this.createUserUseCase.execute(
      CreateUserUseCase.Input(
        username = request.username,
        password = request.password,
        passwordConfirmation = request.passwordConfirmation,
        name = request.name,
        role = request.role
      )
    )
    return ResponseEntity.status(HttpStatus.CREATED).body(output)
  }

}
