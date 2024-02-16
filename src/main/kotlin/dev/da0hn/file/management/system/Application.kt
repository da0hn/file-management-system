package dev.da0hn.file.management.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class FileManagementSystemApplication {
//  @Bean
//  fun runnable(): CommandLineRunner {
//    return CommandLineRunner {
//      val encode1 = BCryptPasswordEncoder().encode("admin")
//      val encode2 = BCryptPasswordEncoder().encode("simple_user_a")
//      val encode3 = BCryptPasswordEncoder().encode("simple_user_b")
//      val encode4 = BCryptPasswordEncoder().encode("simple_user_c")
//      println()
//    }
//  }
}

fun main(args: Array<String>) {
  runApplication<FileManagementSystemApplication>(*args)
}
