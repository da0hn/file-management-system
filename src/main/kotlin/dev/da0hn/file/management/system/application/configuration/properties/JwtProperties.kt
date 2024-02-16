package dev.da0hn.file.management.system.application.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.config.jwt")
data class JwtProperties(
  val secret: String,
  val refreshSecret: String,
  val expiration: java.time.Duration,
  val refreshExpiration: java.time.Duration,
)
