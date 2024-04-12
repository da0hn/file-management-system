package dev.da0hn.file.management.system.application.controller.dto

import dev.da0hn.file.management.system.core.domain.Role

data class CreateUserRequest(
  val name: String,
  val username: String,
  val password: String,
  val passwordConfirmation: String,
  val role: Role,
)
