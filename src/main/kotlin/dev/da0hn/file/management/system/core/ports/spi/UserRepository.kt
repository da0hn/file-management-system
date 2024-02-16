package dev.da0hn.file.management.system.core.ports.spi

import dev.da0hn.file.management.system.core.domain.User

interface UserRepository {

  fun findByUsername(username: String) : User?

}
