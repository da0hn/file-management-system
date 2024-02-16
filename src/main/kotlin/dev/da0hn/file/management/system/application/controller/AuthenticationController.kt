package dev.da0hn.file.management.system.application.controller

import dev.da0hn.file.management.system.application.controller.dto.LoginRequest
import dev.da0hn.file.management.system.core.ports.api.UserAuthenticationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
  private val userAuthenticationUseCase: UserAuthenticationUseCase,
) {

  @PostMapping("/signin")
  fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<UserAuthenticationUseCase.Output> {
    val output = userAuthenticationUseCase.execute(
      UserAuthenticationUseCase.Input(
        loginRequest.username,
        loginRequest.password
      ))
    return ResponseEntity.ok(output)
  }

}
