package dev.da0hn.file.management.system.application.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration {

  @Bean
  fun corsMappingConfigurer(): WebMvcConfigurer {
    // https://reflectoring.io/spring-cors/
    return object : WebMvcConfigurer {
      override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT"
          )
      }
    }
  }

}
