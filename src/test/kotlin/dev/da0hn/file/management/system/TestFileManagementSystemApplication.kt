package dev.da0hn.file.management.system

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestFileManagementSystemApplication {

  @Bean
  @ServiceConnection
  fun mysqlContainer(): MySQLContainer<*> {
    return MySQLContainer(DockerImageName.parse("mysql:latest"))
  }

  @Bean
  @ServiceConnection(name = "openzipkin/zipkin")
  fun zipkinContainer(): GenericContainer<*> {
    return GenericContainer(DockerImageName.parse("openzipkin/zipkin:latest")).withExposedPorts(9411)
  }

}

fun main(args: Array<String>) {
  fromApplication<FileManagementSystemApplication>().with(TestFileManagementSystemApplication::class).run(*args)
}
