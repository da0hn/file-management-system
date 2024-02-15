package dev.da0hn.file.management.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class FileManagementSystemApplication

fun main(args: Array<String>) {
  runApplication<FileManagementSystemApplication>(*args)
}
