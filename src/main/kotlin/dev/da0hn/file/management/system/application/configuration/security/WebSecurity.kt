package dev.da0hn.file.management.system.application.configuration.security

import dev.da0hn.file.management.system.core.ports.spi.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class WebSecurity(
  private val userDetailsServiceAdapter: UserDetailsService,
  private val jwtService: JwtService,
) {

  @Bean
  fun authenticationProvider(): DaoAuthenticationProvider {
    val provider = DaoAuthenticationProvider()
    provider.setUserDetailsService(userDetailsServiceAdapter)
    provider.setPasswordEncoder(this.passwordEncoder())
    return provider
  }

  @Bean
  fun authenticationTokenFilter(): AuthenticationTokenFilter {
    return AuthenticationTokenFilter(userDetailsServiceAdapter, jwtService)
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @Bean
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
    return authenticationConfiguration.authenticationManager!!
  }

  @Bean
  fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
    http.csrf { it.disable() }
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authorizeHttpRequests {
        it.requestMatchers("/actuator/**").permitAll()
//          .requestMatchers("/**/swagger-ui/**").permitAll()
//          .requestMatchers("/**/swagger-resources/**").permitAll()
//          .requestMatchers("/**/v3/api-docs/**").permitAll()
          .requestMatchers("/auth/**").permitAll()
//          .requestMatchers("/users/**").permitAll()
          .anyRequest().authenticated()
      }
    http.authenticationProvider(authenticationProvider())
    http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    return http.build()
  }

}
